package methodarea;

/**
 * Created by wangmeng on 9/7/16.
 */
public class ConstantPoolOOMTest2 {
    public static void main(String[] args) {
        String string1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(string1.intern() == string1);

        String string2 = new StringBuilder("ja").append("va").toString();
        System.out.println(string2.intern() == string2);
    }
    /**
     * JDK1.6: false false
     * JDK1.7: true false
     */
}
