package javabasic.gc;

/**
 * Created by matt on 5/19/16.
 */
public class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String toString(){
		return "Person[name="+name+", age="+age+"]";
	}
}
