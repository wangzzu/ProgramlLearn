package javabasic.collection.map;

import java.util.HashMap;

/**
 * Created by matt on 5/12/16.
 */
public class HashMapTest {
	public static void main(String[] args) {
		HashMap<Integer,String> map=new HashMap<>();
		map.put(null,"ss");
		System.out.println(map.get(null));
		map.put(1, null);
		System.out.println(map.get(1));
	}
}
