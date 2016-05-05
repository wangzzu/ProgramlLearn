package chartroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by matt on 5/4/16.
 */

public class Server {
	private ConcurrentHashMap<String, User> userList = new ConcurrentHashMap<String, User>();

	public static void main(String[] args) {
		new Server();
	}

	public Server() {
		try {
			ServerSocket server = new ServerSocket(4040);
			System.out.println("Info: Server is start!");

			while (true) {
				Socket socket = server.accept();
				User user = new User(socket); // 每启动一个Socket建立一个User

				ServerIn serverIn = new ServerIn(user);
				new Thread(serverIn).start();
				ServerOut serverOut = new ServerOut(user);
				new Thread(serverOut).start();
			}
		} catch (IOException e) {
			System.out.println("Error: 0!");
		}
	}

	/**
	 * Server端接收并处理信息
	 */
	public class ServerIn implements Runnable {
		private String name;
		private User user;
		private PrintWriter pw;
		private BufferedReader userIn;

		public ServerIn(User user) {
			this.user = user;
		}

		public void run() {
			try {
				userIn = new BufferedReader(new InputStreamReader(user.getSocket().getInputStream()));
				pw = new PrintWriter(user.getSocket().getOutputStream(), true);
				login();
				System.out.println(user.getName() + " has logined! " + " Total online user: " + userList.size());

				String msg = userIn.readLine();
				while (msg != null && user.getIsonline() == true) {//当用户在线且输入不为空时执行
					char first = msg.charAt(0);
					char second = msg.charAt(1);
					if (first == '/' && second != '/') { //命令消息
						sendCommand(msg);
					} else if (first == '/' && second == '/') {//预设消息
						sendPremsg(msg);
					} else {//广播消息
						sendToAll(msg);
					}
					msg = userIn.readLine();
				}
				if (user.getIsonline() == false) {//当用户退出后关闭Socket等
					cleanup();
				}
			} catch (IOException ex) {
				System.out.println("Error: " + ex);
			} catch (InterruptedException ex) {
				System.out.println("Counldn't put the message into the queue! " + ex);
			}
		}

		/**
		 * 判断用户登录是否成功
		 */
		private void login() throws IOException, InterruptedException { // 让上一级处理异常
			String regex = "/login\\s+\\w+";
			while (!user.getIsonline()) {
				pw.println("Please login: ");
				String msg = userIn.readLine();
				if (msg.matches(regex)) {
					name = msg.split("\\s+")[1].trim(); // 这地方需要测试
					user.setName(name);
					if (userList.containsKey(name)) {
						pw.println("Name exist, please choose another name.  Please login: ");
					} else {
						userList.put(name, user);
						sendToAll("have logined.");
						user.setIsonline(true);
					}
				}
			}
		}

		/**
		 * user发送广播消息
		 */
		private void sendToAll(String message) {
			Iterator<String> users = userList.keySet().iterator();
			String hostName = user.getName();
			while (users.hasNext()) {
				String aimName = users.next();
				if (message.equals("have logined.")) {  //登录信息
					if (hostName.equals(aimName)) {
						sendToOne(hostName, "You have logined.");
					} else {
						sendToOne(aimName, hostName + " has logined. ");
					}
				} else if (message.equals("have quit!")) {  //退出信息,只需要向其他人广播此信息
					if (!hostName.equals(aimName)) {
						sendToOne(aimName, hostName + " has quit! ");
					}
				} else {//群聊信息
					if (hostName.equals(aimName)) {
						sendToOne(hostName, "I said: " + message);
					} else {
						sendToOne(aimName, hostName + " said: " + message);
					}
				}
			}
		}

		/**
		 * User发送单播消息
		 */
		private void sendToOne(String aimName, String message) {
			try {
				if (userList.containsKey(aimName)) {
					userList.get(aimName).getQueue().put(message);//将消息加入对象的消息queue
				} else {
					user.getQueue().put(name + " is not online.");
				}
			} catch (InterruptedException e) {
				System.out.println(user.getName() + " canont put the message to " + aimName + "!!!");
			}
		}

		/**
		 * Server端处理命令消息，主要是/who、/to、/history、/login
		 *
		 * @param
		 */
		private void sendCommand(String message) throws InterruptedException {
			if (message.equals("/quit")) {
				pw.println("You have quit!");//成功退出
				userList.remove(name);
				user.setIsonline(false);
				System.out.println(user.getName() + " has quit. " + " Total online user: " + userList.size());
				sendToAll("have quit!");
			} else {
				String[] msgs = message.split("\\s+", 3);
				String command = msgs[0].substring(1);
				switch (command) {
					case "login":
						user.getQueue().put("You have logined, if you want login again, please quit first.");
						break;
					case "who":
						Iterator<String> users = userList.keySet().iterator();
						while (users.hasNext()) {
							user.getQueue().put(users.next());
						}
						user.getQueue().put("Total online user: " + userList.size());
						break;
					case "to":
						if (msgs.length != 3) {
							user.getQueue().put("Format Error! Please input like:");
							user.getQueue().put("/to username msg");
						} else {
							String aimName = msgs[1];
							String message0 = msgs[2];
							if (aimName.equals(name)) {
								user.getQueue().put("Stop talking to yourself!");
							} else if (!userList.containsKey(aimName)) {
								user.getQueue().put(aimName + "  is not online.");
							} else {
								user.getQueue().put("You said  to " + aimName + " :  " + message0);
								sendToOne(aimName, name + " said to you in private. :  " + message0);
							}
						}
						break;
					case "history":
						/**
						 *等待实现
						 */
						break;
					default:
						user.getQueue().add("Error, what you input is wrong!");
						break;
				}
			}
		}

		/**
		 * 处理预设消息
		 */
		private void sendPremsg(String msg) throws InterruptedException {
			switch (msg) {
				case "//hi":
					sendToAll("Hello to everyone");
					break;
				case "//smile":
					sendToAll("脸上泛起无邪的笑容");
					break;
				case "//cry":
					sendToAll("我已经泪流满面");
					break;
				case "//nosaid":
					sendToAll("我很无语啊");
					break;
				default:
					user.getQueue().add("What you input is invalid!");
					break;
			}
		}

		/**
		 * 用户退出后的清理函数
		 */
		private void cleanup() throws IOException {
			user.getSocket().close();
			pw.close();
			userIn.close();
		}
	}

	/**
	 * Server端发送储存在User队列Queue中的消息
	 */
	public class ServerOut implements Runnable {
		private User user;

		public ServerOut(User user) {
			this.user = user;
		}

		public void run() {
			try {
				// 中文传输的问题
				PrintWriter pw = new PrintWriter(user.getSocket().getOutputStream(), true);
				while (true) {
					String msg = user.getQueue().take();
					pw.println(msg);
				}
			} catch (InterruptedException e) {
				System.out.println("Error: 2!");
			} catch (IOException ex) {
				System.out.println("Error: 3!");
			}
		}
	}

}