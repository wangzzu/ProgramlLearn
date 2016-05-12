package SingletonPattern;

/**
 * 双检锁/双重校验锁
 *
 * 之所以进行第二次检查，因为在多个线程一起进入同步块外的if时，如果在同步块内不进行第二次检查的话就会生成多个实例了。
 * volatile ：使用 volatile 的主要原因是其另一个特性：禁止指令重排序优化，
 * Created by matt on 5/8/16.
 */
public class SingleObject4 {
	private volatile static SingleObject4 instance;

	private SingleObject4() {
	}

	public static SingleObject4 getInstance() {
		if (instance == null) {
			synchronized (SingleObject4.class) {
				if (instance == null) {
					instance = new SingleObject4();
				}
			}
		}
		return instance;
	}

	public void showMessage() {
		System.out.println("4: Hello World!");
	}
}
