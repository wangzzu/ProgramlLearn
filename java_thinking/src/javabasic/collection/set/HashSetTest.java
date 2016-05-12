package javabasic.collection.set;

import javabasic.collection.Person;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by matt on 5/9/16.
 */
public class HashSetTest {
	public static void main(String[] args) {
		HashSet<Person> set = new HashSet<>();
		set.add(new Person("wm", 12));
		System.out.println(set.contains(new Person("wm", 123)));

		HashMap<String, String> map = new HashMap<>();
		map.put(null, null);
		StringBuilder stringBuilder = new StringBuilder("l");
	}
}
