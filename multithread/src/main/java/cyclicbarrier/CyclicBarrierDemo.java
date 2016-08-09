package cyclicbarrier;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by matt on 16/8/9.
 */

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        int totalThread = 5;
        final CyclicBarrier barrier = new CyclicBarrier(totalThread);

        for (int i = 0; i < totalThread; i++) {
            final String threadName = "Thread " + i;
            Thread thread=new Thread(){
                @Override
                public void run() {
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, " is waiting"));
                    try {
                        barrier.await();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
                }
            };
            thread.start();
        }
    }
}
