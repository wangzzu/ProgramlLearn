package wait;

import java.util.Date;

/**
 * 测试一下 wait 的用法,wait
 * Created by matt on 16/8/7.
 */
public class Wait {
    public synchronized static void wait1(){
        try {
            System.out.println(new Date() + " Thread1 is running");
            Wait.class.wait();
            System.out.println(new Date() + " Thread1 ended");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized static void notify1(){
        try {
            System.out.println(new Date() + " Thread2 is running");
            Wait.class.notify();
            // Don't use sleep method to avoid confusing
            for(long i = 0; i < 2000; i++) {
            }
            System.out.println(new Date() + " Thread2 release lock");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        wait1();
        notify1();
    }
}
