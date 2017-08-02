package javabasic.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
//: io/Logon.java
//Demonstrates the "transient" keyword.
import java.util.concurrent.TimeUnit;

public class Login implements Serializable {
	private Date date = new Date();
	private String username;
	private transient String password;

	public Login(String name, String pwd) {
		username = name;
		password = pwd;
	}

	public String toString() {
		return "logon info: \n   username: " + username + "\n   date: " + date + "\n   password: " + password;
	}

	public static void main(String[] args) throws Exception {
		Login a = new Login("Hulk", "myLittlePony");
		System.out.println("logon a = " + a);
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("/home/matt/Logon.out"));
		o.writeObject(a);
		o.close();
		TimeUnit.SECONDS.sleep(1); // Delay
		// Now get them back:
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("/home/matt/Logon.out"));
		System.out.println("Recovering object at " + new Date());
		a = (Login) in.readObject();
		System.out.println("logon a = " + a);
	}
} /*
* logon a = logon info:
*   username: Hulk
*    date: Tue May 17 11:11:28 CST 2016
*    password: myLittlePony
* Recovering object at Tue May 17 11:11:29 CST 2016
* logon a = logon info:
*    username: Hulk
*    date: Tue May 17 11:11:28 CST 2016
*    password: null
*/