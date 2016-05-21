package javabasic.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by matt on 5/21/16.
 */

class Base implements Serializable {
	private int id;
	private String msg;

	//	public Base(){
//		this.id=1;
//		this.msg="1";
//	}
	public Base(int id, String msg) {
		this.id = id;
		this.msg = msg;
	}
}

class Son extends Base implements Serializable {
	private int id;
	private String msg;

	public Son(int id, String msg) {
		super(id, msg);
//		this.id=id;
//		this.msg=msg;
	}

	public void show() {
		System.out.println("hello! from [" + id + " " + msg + "].");
	}
}

public class BaseClassTest {
	public static void main(String[] args) {
		ObjectOutputStream o = null;
		try {
			o = new ObjectOutputStream(new FileOutputStream("/home/matt/son.out"));
			o.writeObject(new Son(1, "h1"));
			o.writeObject(new Son(2, "h2"));
			o.close();
			// Now get them back:
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("/home/matt/son.out"));
			try {
				Son son1 = (Son) in.readObject();
				son1.show();
				Son son2 = (Son) in.readObject();
				son2.show();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
