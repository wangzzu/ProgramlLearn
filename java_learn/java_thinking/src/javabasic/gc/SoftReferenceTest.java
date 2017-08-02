package javabasic.gc;

import java.lang.ref.SoftReference;

/**
 * 合理的使用软引用
 *
 * 当系统内存空间足够时，它不会被系统回收，程序也可使用该对象；当系统内存空间不足时，系统将会回收它。
 * Created by matt on 5/19/16.
 */
public class SoftReferenceTest {
	public static void main(String[] args) {
		SoftReference<Person>[] people=new SoftReference[100];
		for (int i = 0; i < people.length; i++) {
			people[i]=new SoftReference<Person>(new Person("name"+i,(i+1)*4%100));
		}
		System.out.println(people[2].get());
		System.out.println(people[5].get());
		// 通知系统进行垃圾回收
		System.gc();
		System.runFinalization();
		// 垃圾回收运行之后，因为内存空间足够，SoftReference数组里的元素并不会被回收
		System.out.println(people[2].get());
		System.out.println(people[5].get());

		SoftReference<Person>[] people1=new SoftReference[100000];
		for (int i = 0; i < people1.length; i++) {
			people1[i]=new SoftReference<Person>(new Person("name"+i,(i+1)*4%100));
		}
		System.out.println();
		System.out.println(people1[2].get());
		System.out.println(people1[5].get());
		// 通知系统进行垃圾回收
		System.gc();
		System.runFinalization();
		// 数组改为10000时，就会造成内存不足，进而回收该对象
		System.out.println(people1[2].get());
		System.out.println(people1[5].get());
	}
}
