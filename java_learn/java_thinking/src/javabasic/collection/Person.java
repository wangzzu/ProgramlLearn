package javabasic.collection;

/**
 * Created by matt on 5/9/16.
 */
public class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o.getClass() == Person.class) {
			Person per = (Person) o;
			return this.name.equals(per.name);
		}
		return false;
	}

	public int hashCode(){
		return name.hashCode();
	}
}
