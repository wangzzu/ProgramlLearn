package filetransmit;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by matt on 5/5/16.
 */
public class FileClient {
	private Socket client;
	private File file;
	private FileInputStream fis;
	private DataOutputStream dos;

	public static void main(String[] args) {
		new FileClient("/home/matt/1.pdf");
	}

	public FileClient(String filePath) {
		try {
			try {
				client = new Socket("127.0.0.1", 4040);
				file = new File(filePath);
				fis = new FileInputStream(file);
				dos = new DataOutputStream(client.getOutputStream());

				dos.writeUTF(file.getName());
				dos.flush();
				dos.writeLong(file.length());
				dos.flush();

				byte[] sendBytes = new byte[1024]; // 缓冲区
				int length = 0;
				while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
					dos.write(sendBytes, 0, length);
					dos.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (dos != null || fis != null) {
					dos.close();
					fis.close();
				}
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
