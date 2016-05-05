package chartroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端
 * Created by matt on 5/4/16.
 */

public class Client {
	public Client() {
		try {
			Socket socket = new Socket("127.0.0.1", 4040);
			PrintWriter os = new PrintWriter(socket.getOutputStream(), true); // true: autoflush()
			ClientIn clientIn = new ClientIn(socket, os);
			new Thread(clientIn).start();
			ClientOut clientOut = new ClientOut(socket);
			new Thread(clientOut).start();
		} catch (Exception e) {
			System.out.println("Error: "+e);
		}
	}

	/**
	 * client端接收输入消息
	 */
	public class ClientIn implements Runnable {
		private Socket socket;
		private PrintWriter os;
		private BufferedReader stdIn;

		public ClientIn(Socket socket, PrintWriter os) {
			this.socket = socket;
			this.os = os;
		}

		public void run() {
			try {
				stdIn = new BufferedReader(new InputStreamReader(System.in));
				String msg;
				while ((msg = stdIn.readLine()) != null) {
					os.println(msg);
				}
			} catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
	}

	/**
	 * client端输出消息
	 */
	public class ClientOut implements Runnable {
		private Socket socket;

		public ClientOut(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				while (true) {
					BufferedReader clientOutput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String msg = clientOutput.readLine();
					if (!msg.equals("null")) {
						System.out.println(msg);
					}
				}

			} catch (IOException e) {
				System.out.println("Error: "+e);
			}
		}
	}

	public static void main(String[] args) {
		new Client();
	}
}