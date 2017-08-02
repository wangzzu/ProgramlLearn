package javabasic.collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by matt on 5/12/16.
 */
public class LinkedHashMapTest {
	public static void main(String[] args) {
		Map<String, String> hashmap = new HashMap<>();
		hashmap.put("apple", "苹果");
		hashmap.put("watermelon", "西瓜");
		hashmap.put("banana", "香蕉");
		hashmap.put("peach", "桃子");

		Iterator iter1 = hashmap.entrySet().iterator();
		System.out.println("HashMap:");
		while (iter1.hasNext()) {
			Map.Entry entry = (Map.Entry) iter1.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}

		Map<String, String> linkedmap = new LinkedHashMap<>(16, 0.75f); // 默认是按插入顺序
		linkedmap.put("apple", "苹果");
		linkedmap.put("watermelon", "西瓜");
		linkedmap.put("banana", "香蕉");
		linkedmap.put("peach", "桃子");

		Iterator iter2 = linkedmap.entrySet().iterator();
		System.out.println("\nLinkedHashMap（插入顺序）:");
		while (iter2.hasNext()) {
			Map.Entry entry = (Map.Entry) iter2.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}

		Map<String, String> linkedmap2 = new LinkedHashMap<>(16, 0.75f,true); // 默认是按访问顺序
		linkedmap2.put("apple", "苹果");
		linkedmap2.put("watermelon", "西瓜");
		linkedmap2.put("banana", "香蕉");
		linkedmap2.put("peach", "桃子");
		linkedmap2.get("apple");
		linkedmap2.get("banana");

		Iterator iter3 = linkedmap2.entrySet().iterator();
		System.out.println("\nLinkedHashMap（访问顺序）:");
		while (iter3.hasNext()) {
			Map.Entry entry = (Map.Entry) iter3.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
}
