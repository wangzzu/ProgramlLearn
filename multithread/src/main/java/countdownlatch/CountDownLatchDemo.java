package countdownlatch;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by matt on 16/8/9.
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int totalThread = 3;
        long start = System.currentTimeMillis();
        final CountDownLatch countDown = new CountDownLatch(totalThread);
        for (int i = 0; i < totalThread; i++) {
            final String threadName = "Thread " + i;
            Thread thread=new Thread() {
                    public void run() {
                        System.out.println(String.format("%s\t%s %s", new Date(), threadName, "started"));
                        try {
                            Thread.sleep(1000);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        countDown.countDown();
                        System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
                    }
            };
            thread.start();
        }
        countDown.await();
        long stop = System.currentTimeMillis();
        System.out.println(String.format("Total time : %sms", (stop - start)));
    }
}
