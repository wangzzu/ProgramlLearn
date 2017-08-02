package javabasic.gc;

import java.lang.ref.WeakReference;

/**
 * 弱引用示例
 * 弱引用的生存周期更短，对于弱引用的对象，当系统进行垃圾回收时，不管系统内存是否足够，总会回收该对象所占用的内存
 * Created by matt on 5/19/16.
 */
public class WeakReferenceTest {
	public static void main(String[] args) {
		WeakReference<Person>[] pe = new WeakReference[10];
		for (int i = 0; i < 10; i++) {
			pe[i] = new WeakReference(new Person("name" + i, (i + 1) * 4 % 100));
		}
		System.out.println(pe[2].get());
		System.out.println(pe[5].get());
		// 通知系统进行垃圾回收
		System.gc();
		System.runFinalization();
		// 垃圾回收运行
		System.out.println(pe[2].get());
		System.out.println(pe[5].get());

		System.out.println("\n使用弱引用，如何避免空指针错误的例子:");
		WeakReference<Person> wr = new WeakReference<Person>(new Person("matt", 24));
		System.out.println(wr.get());
		// 通知系统进行垃圾回收
		System.gc();
		System.runFinalization();
		// 对于弱引用对象，有可能会被gc了，所以在使用时需要进行判断，为null时需要重新建立引用
		Person person = wr.get();
		System.out.println(person);
		if (person == null) {
			person = new Person("matt", 24);
			wr = new WeakReference<Person>(person);
		}
		System.out.println(person);
		person = null;
	}
}
