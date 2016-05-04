package multithread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by matt on 5/4/16.
 */
public class Client {
	public static void main(String[] args) {
		try {
			//向本机的4040端口发出客户端请求
			Socket socket = new Socket("127.0.0.1", 4040);

			//由系统标准输入设备构造BufferedReader对象
			BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));

			//由Socket对象得到输出流，并构造PrintWriter对象
			PrintWriter os = new PrintWriter(socket.getOutputStream());

			//由Socket对象得到输入流，并构造相应的BufferedReader对象
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			//从系统标准输入读入一行字符串
			String readLine = null;
			readLine = sysin.readLine();

			while (!readLine.equalsIgnoreCase("bye")) {

				os.println(readLine); // 将从系统标准输入读入的字符串输出到Server
				os.flush(); // 刷新输出流，使Server马上收到该字符串
				System.out.println("Client:" + readLine);
				System.out.println("Server:" + is.readLine()); // 从Server读入一字符串，并打印到标准输出上

				readLine = sysin.readLine(); //从系统标准输入读入一字符串
			}
			os.close(); // 关闭Socket输出流
			is.close(); // 关闭Socket输入流
			socket.close(); // 关闭Socket
		} catch (IOException e) {
			System.out.println("Error: "+e);
		}
	}
}
