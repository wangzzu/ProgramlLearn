package javabasic.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by matt on 5/21/16.
 */
public class StoreTest {
	public static void main(String[] args) {
		ObjectOutputStream o = null;
		try {
			o = new ObjectOutputStream(new FileOutputStream("/home/matt/store.out"));
			Person person=new Person("matt1",20,Gender.MALE);
			o.writeObject(person);
			person.setAge(22);
			System.out.println(new File("/home/matt/store.out").length());
			o.writeObject(person);
			System.out.println(new File("/home/matt/store.out").length());
			o.close();
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("/home/matt/store.out"));
			try {
				Person person1= (Person) in.readObject();
				System.out.println(person1.getAge());
				Person person2= (Person) in.readObject();
				System.out.println(person2.getAge());
				System.out.println(person1==person2);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
