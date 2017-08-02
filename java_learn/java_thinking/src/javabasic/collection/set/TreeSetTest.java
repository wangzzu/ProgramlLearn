package javabasic.collection.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by matt on 5/13/16.
 */
public class TreeSetTest {
	public static void main(String[] args) {
		TreeSet<String> treeSet=new TreeSet<>();
		treeSet.add("wm0");
		treeSet.add("wm1");
		treeSet.add("matt0");
		treeSet.add("matt1");
		Iterator tree=treeSet.iterator();
		System.out.println("HashSet(默认排序规则):");
		while (tree.hasNext()){
			System.out.println(tree.next());
		}

		TreeSet<String> treeSet1 = new TreeSet<String>(new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				String a = (String)o1;
				String b = (String)o2;
				return -a.compareTo(b);
			}});
		treeSet1.add("wm0");
		treeSet1.add("wm1");
		treeSet1.add("matt0");
		treeSet1.add("matt1");
		System.out.println("\nHashSet(向反的默认排序规则):");
		Iterator tree1=treeSet1.iterator();
		while (tree1.hasNext()){
			System.out.println(tree1.next());
		}
	}
}
