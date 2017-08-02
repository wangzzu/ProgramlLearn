package javabasic.memory;

/**
 * Created by matt on 5/10/16.
 */
public class Person {
	private String name;
	private int age;
	static int eyeNum;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void info() {
		System.out.println("我的名字是： " + name + ", 我的年龄是： " + age);
	}
}
