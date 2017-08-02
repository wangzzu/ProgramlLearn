package javabasic.trap;

/**
 * Created by matt on 5/19/16.
 */
public class StringTest {
	public static void main(String[] args) {
		Integer int1 = 1;
		Integer int2 = 1;
		String str1 = "wm";
		String str2 = "wm";
		System.out.println(int1 == int2); // 对于Java程序中的字符直接量，JVM会使用一个字符串池保存它们
		System.out.println(str1 == str2); // 一般情况下，字符串池中字符串对象不会被垃圾回收
		System.out.println(System.identityHashCode(str1));
		System.out.println(str1.equals(str2));
		System.out.println(str1.compareTo(str2)); // 值为0时，代表两个字符串的字符数组时相同的
	}
}
