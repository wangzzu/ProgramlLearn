package heap;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError
 * Created by wangmeng on 9/7/16.
 */
public class HeapOOMTest {
    static class Person {
        private final static String DEFAULT_NAME = "matt";
        private final static int DEFAULT_AGE = 18;
        private String name;
        private int age;

        public Person() {
            this.name = DEFAULT_NAME;
            this.age = DEFAULT_AGE;
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
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
    }

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();
        while (true) {
            persons.add(new Person());
        }
    }
    /**
     * Output
     *
     * java.lang.OutOfMemoryError: Java heap space
     * Dumping heap to java_pid9479.hprof ...
     * Heap dump file created [11824236 bytes in 0.092 secs]
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     *  at java.util.Arrays.copyOf(Arrays.java:2245)
     *  at java.util.Arrays.copyOf(Arrays.java:2219)
     *  at java.util.ArrayList.grow(ArrayList.java:242)
     *  at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:216)
     *  at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:208)
     *  at java.util.ArrayList.add(ArrayList.java:440)
     *  at heap.HeapOOMTest.main(HeapOOMTest.java:47)
     *  at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     *  at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
     *  at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     *  at java.lang.reflect.Method.invoke(Method.java:606)
     *  at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
     */
}

