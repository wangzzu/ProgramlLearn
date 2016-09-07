package heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangmeng on 9/7/16.
 */
public class HeapOOMTest {
    private static class Person {
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
}
