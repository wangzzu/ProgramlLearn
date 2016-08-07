package wait;

import java.util.Date;

/**
 * Created by matt on 16/8/7.
 */
public class Sleep {

    public synchronized static void wait1() {
        try {
            System.out.println(new Date() + " Thread1 is running");
            Thread.sleep(2000);
            System.out.println(new Date() + " Thread1 ended");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized static void notify1() {
        try {
            System.out.println(new Date() + " Thread2 is running");
            Thread.sleep(2000);
            for (long i = 0; i < 2000000; i++) {
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
