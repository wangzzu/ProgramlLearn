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
public class StaticTest implements Serializable{
	private static int id=10;

	public static void main(String[] args) {
		System.out.println("Constructing objects:");
		ObjectOutputStream o = null;
		try {
			o = new ObjectOutputStream(new FileOutputStream("/home/matt/static.out"));
			o.writeObject(new StaticTest());
			o.close();
			StaticTest.id=0;
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("/home/matt/static.out"));
			StaticTest staticTest = null;
			try {
				staticTest = (StaticTest) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println(staticTest.id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
