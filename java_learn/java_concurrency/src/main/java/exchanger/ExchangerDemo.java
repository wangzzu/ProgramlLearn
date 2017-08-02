package exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by matt on 16/8/9.
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        final Exchanger<List<Integer>> exchanger = new Exchanger<List<Integer>>();
        new Thread() {
            @Override
            public void run() {
                List<Integer> list = new ArrayList<Integer>(2);
                list.add(1);
                list.add(2);
                try {
                    list = exchanger.exchange(list);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread1" + list);
            }
        }.start();
        new Thread(){
            @Override
            public void run(){
                List<Integer> list=new ArrayList<Integer>(2);
                list.add(4);
                list.add(5);
                try {
                    list=exchanger.exchange(list);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2" + list);
            }
        }.start();
    }
}
