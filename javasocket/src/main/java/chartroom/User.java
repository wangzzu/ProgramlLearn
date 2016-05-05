package chartroom;

import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Server端建立的User对象，User对象的主要作用：
 * 1.提供一个Queue方法，来记录Client端的消息
 * 2.提供一个状态量isonline，显示Client是否在线;
 * 3.记录User的用户名.
 * Created by matt on 5/4/16.
 */

public class User {
	private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();//可以预设消息队列的长度
	private boolean isOnline; //是否在线
	private String name;
	private Socket socket;

	public User(Socket socket) {
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public LinkedBlockingQueue<String> getQueue() {
		return queue;
	}

	public void setIsonline(boolean boo) {
		this.isOnline = boo;
	}

	public boolean getIsonline() {
		return isOnline;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
