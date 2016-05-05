package filetransmit;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by matt on 5/5/16.
 */
public class FileServer {
	private ServerSocket serverSocket;
	private Socket client;
	private DataInputStream dis;
	private FileOutputStream fos;

	public static void main(String[] args) {
		new FileServer("/home/matt/code");
	}
	public FileServer(String path) {
		try {
			try {
				serverSocket = new ServerSocket(4040);
				client=serverSocket.accept();
				dis = new DataInputStream(client.getInputStream());

				String fileName = dis.readUTF();
				long fileLength = dis.readLong();
				fos = new FileOutputStream(new File(path + "/" + fileName));

				byte[] sendBytes = new byte[1024];
				int transLen = 0;
				System.out.println("Info: 开始接收文件 " + fileName + ", 文件大小为 " + fileLength + ".");
				while (true) {
					int read = 0;
					read = dis.read(sendBytes);
					if (read == -1) {
						break;
					}
					transLen += read;
					System.out.println("Info: 接收文件进度 " + 100 * transLen / fileLength + "%.");
					fos.write(sendBytes, 0, read);
					fos.flush();
				}
				System.out.println("Info: 接收文件 " + fileName + "成功.");
				client.close();
			} catch (IOException e) {
				System.out.println("Error: " + e);
			} finally {
				if (dis != null || fos != null) {
					dis.close();
					fos.close();
				}
				serverSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
