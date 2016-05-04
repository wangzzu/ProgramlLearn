package javaBasic.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by matt on 4/28/16.
 */
public class MainThread {
	public static void main(String[] args) {
		LiftOff launch = new LiftOff(); // 使用mian()线程来启动LiftOff线程（run()并不是由单独的线程启动）
		launch.run();
		System.out.println("\n");

		Thread t = new Thread(new LiftOff()); // 使用Thread构造器来启动线程，此时run()由不同的线程来执行
		t.start();
		System.out.println("Waiting for LiftOff!");

		//更清楚地看出这几个Thread是由不同的线程来启动的
		for(int i=0;i<5;i++){
			(new Thread(new LiftOff())).start();
		}
		System.out.println("/n");

		//使用Executor
		ExecutorService exec= Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}
}