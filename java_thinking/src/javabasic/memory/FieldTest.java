package javabasic.memory;

/**
 * Created by matt on 5/10/16.
 */
public class FieldTest {
	public static void main(String[] args) {
		Person.eyeNum=2;
		System.out.println("0: " + Person.eyeNum);
		Person p1=new Person("wm1",12);
		System.out.println("P1: " + p1.eyeNum);
		p1.info();

		Person p2=new Person("wm2",22);
		System.out.println("P2: " + p2.eyeNum);
		p2.info();
		p2.eyeNum=3;

		System.out.println("P2: " + p2.eyeNum);
	}
}
