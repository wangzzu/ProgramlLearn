package javabasic.collection.map;

import java.util.Hashtable;

/**
 * Created by matt on 5/12/16.
 */
public class HashTableTest {
	public static void main(String[] args) {
		Hashtable<Integer,String> hashtable=new Hashtable<>();
		hashtable.put(null,"ss");
		System.out.println(hashtable.get(null));
	}
}
