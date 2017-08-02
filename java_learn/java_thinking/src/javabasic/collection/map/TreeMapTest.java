package javabasic.collection.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by matt on 5/13/16.
 */
public class TreeMapTest {
	public static void main(String[] args) {
		TreeMap<String, String> treeMap = new TreeMap<>();
		HashMap<String, String> hashMap = new HashMap<>();

		treeMap.put("a", "wm0");
		treeMap.put("b", "wm1");
		treeMap.put("c", "wm2");
		treeMap.put("d", "wm3");
		Iterator tree = treeMap.keySet().iterator();
		System.out.println("TreeMap:");
		while (tree.hasNext()) {
			Object key = tree.next();
			System.out.println(key.toString() + " " + treeMap.get(key));
		}


		TreeMap<String,String> treeMap1 = new TreeMap<String,String>(new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				String a = (String)o1;
				String b = (String)o2;
				return -a.compareTo(b);
			}});
		treeMap1.put("a", "wm0");
		treeMap1.put("b", "wm1");
		treeMap1.put("c", "wm2");
		treeMap1.put("d", "wm3");
		Iterator tree1 = treeMap1.keySet().iterator();
		System.out.println("\nTreeMap（根据value排序）:");
		while (tree1.hasNext()) {
			Object key = tree1.next();
			System.out.println(key.toString() + " " + treeMap1.get(key));
		}

		hashMap.put("a", "wm0");
		hashMap.put("b", "wm1");
		hashMap.put("c", "wm2");
		hashMap.put("d", "wm3");
		Iterator hash = hashMap.keySet().iterator();
		System.out.println("\nHashMap:");
		while (hash.hasNext()) {
			Object key = hash.next();
			System.out.println(key.toString() + " " + hashMap.get(key));
		}


	}
}
