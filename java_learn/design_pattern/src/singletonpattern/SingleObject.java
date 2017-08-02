package singletonpattern;

/**
 * SingleObject
 * 懒汉式，线程不安全
 * 这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
 * 
 * @author matt
 *
 */
public class SingleObject {
	private static SingleObject instance = new SingleObject();

	private SingleObject() {
	}

	public static SingleObject getInstance() {
		return instance;
	}

	public void showMessage() {
		System.out.println("Hello World!");
	}
}
