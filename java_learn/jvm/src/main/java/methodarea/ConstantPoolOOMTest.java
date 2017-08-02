package methodarea;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-XX: PermSize=4M -XX:MaxPermSize=4M
 * 对 JDK1.7,程序会一直运行下去,而1.6及以前的版本会报错
 * Created by wangmeng on 9/7/16.
 */
public class ConstantPoolOOMTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
