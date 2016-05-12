package javabasic.collection.list;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by matt on 5/12/16.
 */
public class LinkedListTest {
	public static void main(String[] args) {
		LinkedList<String> list1 = new LinkedList<>();
		LinkedList<String> list2 = new LinkedList<>();
		LinkedList<String> list3 = new LinkedList<>();

		// 第一：作为链表
		list1.add("matt0");
		list1.add("matt1");
		list1.add("matt2");
		list1.add("matt3");
		System.out.println("test1: 作为链表");
		System.out.println("list1：" + list1.get(0) + " " + list1.get(1) + " " + list1.get(2) + " " + list1.get(3));
		System.out.println("list1: 移除前 index_1 " + list1.get(1));
		list1.remove(1);
		System.out.println("list1: 移除后 index_1 " + list1.get(1));

		// 第二：作为队列
		list2.add("matt0");
		list2.add("matt1");
		list2.add("matt2");
		list2.add("matt3");
		System.out.println("\ntest2: 作为队列");
		System.out.println(list2.poll()+" "+list2.poll()+" "+list2.poll()+" "+list2.poll());

		//第三：作为栈
		list3.push("matt0");
		list3.push("matt1");
		list3.push("matt2");
		list3.push("matt3");
		System.out.println("\ntest3: 作为栈");
		System.out.println(list3.pop() + " " + list3.pop() + " " + list3.pop() + " " + list3.pop());
	}


}
