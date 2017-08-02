package javabasic.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用示例
 * 虚引用的作用主要是追踪对象被垃圾回收的状态
 * 引用队列java.lang.ref.ReferenceQueue用于保存被回收后对象的引用
 * 如果一个对象只有虚引用，那它和没有引用的效果大致相同，虚引用是不能单独使用的，虚引用必须和引用队列联合使用
 * Created by matt on 5/19/16.
 */
public class PhantomReferenceTest {
	public static void main(String[] args) {
		Person person = new Person("matt", 24);
		ReferenceQueue<Person> referenceQueue = new ReferenceQueue<>(); // 引用队列
		PhantomReference<Person> phantomReference = new PhantomReference<Person>(person, referenceQueue); // 创建一个虚引用
		person = null; // 防止对象游离
		System.out.println(phantomReference.get()); // 程序是不能通过虚引用访问对象
		// gc
		System.gc();
		System.runFinalization();
		System.out.println(referenceQueue.poll() == phantomReference);
	}
}
