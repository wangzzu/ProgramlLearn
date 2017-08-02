package javabasic.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by matt on 5/17/16.
 */
public class Serialize {
	public static void main(String[] args) {
		Person p1 = new Person("wm0", 10, Gender.MALE);
		Person p2 = new Person("wm1", 18, Gender.MALE);

		System.out.println("p1 = " + p1);
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("/home/matt/person.out"));
			out.writeObject("Person1 storage\n");
			out.writeObject(p1);
			out.close(); // Also flushes output
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("/home/matt/person.out"));
			String s = null;
			Person p11 = null;
			try {
				s = (String) in.readObject();
				p11 = (Person) in.readObject();
				System.out.println("after Serialize: " + p11);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			System.out.println("\n"+ "p2 = " + p2);
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream out2 = new ObjectOutputStream(bout);
			out2.writeObject("Person2 storage\n");
			out2.writeObject(p2);
			out2.flush();
			ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
			try {
				s = (String) in2.readObject();
				Person p22 = (Person) in2.readObject();
				System.out.println("after Serialize: " + p22);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
