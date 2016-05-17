package javabasic.serialize;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by matt on 5/16/16.
 */
public class Person implements Serializable{
	private String name;
	private int age;
	private Gender gender;
	private int id;

	public Person(String name, int age, Gender gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.id = (new Random()).nextInt(10);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "[" + name + ", " + age + ", " + gender + ", " + id + "]";
	}
}
