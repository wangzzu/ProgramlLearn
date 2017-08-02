package multithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by matt on 5/4/16.
 */
public class Server {

	static class ServerTask implements Runnable {
		private Socket socket;

		public ServerTask(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				handleSocket(socket);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void handleSocket(Socket socket) throws Exception {
		BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 由Socket对象得到输入流，并构造相应的BufferedReader对象
		PrintWriter os = new PrintWriter(socket.getOutputStream()); // 由Socket对象得到输出流，并构造PrintWriter对象

		String msg = is.readLine();
		String ack = "Received!";
		while (!msg.equalsIgnoreCase("bye")) {
			System.out.println("Client: " + socket.toString() + " " + msg);
			os.println(ack);
			os.flush();
			msg = is.readLine();
		}
		os.close();
		is.close();
		socket.close();
	}

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(4040); // 创建一个ServerSocket监听4040端口
			while (true) {
				Socket socket = serverSocket.accept(); // 使用accept()接收Socket请求，但是accept方法是阻塞式的
				new Thread(new ServerTask(socket)).start();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
}
