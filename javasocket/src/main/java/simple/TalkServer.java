package simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Principal;

/**
 * Created by matt on 5/4/16.
 */
public class TalkServer {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket=new ServerSocket(4040); // 创建一个ServerSocket监听4040端口
			Socket socket=serverSocket.accept(); // 使用accept()接收Socket请求，但是accept方法是阻塞式的

			String msg=null;

			BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream())); // 由Socket对象得到输入流，并构造相应的BufferedReader对象
			PrintWriter os=new PrintWriter(socket.getOutputStream()); // 由Socket对象得到输出流，并构造PrintWriter对象
			BufferedReader sysin=new BufferedReader(new InputStreamReader(System.in)); // 由系统标准输入设备构造BufferedReader对象

			msg=sysin.readLine();

			while (!msg.equalsIgnoreCase("bye")){
				os.println(msg); // 向客户端输出该字符串
				os.flush(); // 刷新输出流，使Client马上收到该字符串
				System.out.println("Server:"+msg);
				System.out.println("Client:"+is.readLine());
				msg=sysin.readLine();
			}
			os.close();
			is.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
}
