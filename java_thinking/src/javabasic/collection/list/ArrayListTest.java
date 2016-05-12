package javabasic.collection.list;

import java.util.ArrayList;

/**
 * Created by matt on 5/12/16.
 */
public class ArrayListTest {
	public static void main(String[] args) {
		ArrayList<Integer> lists=new ArrayList<>();
		lists.add(0);
		lists.add(1);
		lists.add(2);
		lists.add(3);
		System.out.println(lists.size());
		System.out.println(lists.get(1));
		lists.remove(1); // 移除索引1处的元素
		System.out.println(lists.get(1));
	}
}
