package threadpool;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by matt on 16/8/8.
 */
public class ThreadPoolExecutorDemo {
    public static void threadPoolTest(int count) {
        long startTime = System.currentTimeMillis();
        final List<Integer> list = new LinkedList<Integer>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(count));
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            threadPoolExecutor.execute(new Runnable() {
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
        threadPoolExecutor.shutdown();
        try {
            threadPoolExecutor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ThreadPool demo runs "+count+ " times, the total time of spending is: "+(System.currentTimeMillis()-startTime));
        System.out.println(list.size());
    }

    public static void threadTest(int count) {
        long startTime = System.currentTimeMillis();
        final List<Integer> list = new LinkedList<Integer>();
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            Thread thread=new Thread(){
                @Override
                public void run(){
                    list.add(random.nextInt());
                }
            };
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread demo runs "+count+ " times, the total time of spending is: "+(System.currentTimeMillis()-startTime));
        System.out.println(list.size());
    }


    public static void main(String[] args) {
        int count=10000;
        threadPoolTest(count);
        threadTest(count);
    }
}
