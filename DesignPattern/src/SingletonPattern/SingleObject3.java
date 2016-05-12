package SingletonPattern;

/**
 * 饿汉式
 * 这种方式比较常用，但容易产生垃圾对象。
 * 单例会在加载类后一开始就被初始化，即使客户端没有调用 getInstance()方法。
 * 饿汉式的创建方式在一些场景中将无法使用：譬如 Singleton 实例的创建是依赖参数或者配置文件的，
 * 在 getInstance() 之前必须调用某个方法设置参数给它，那样这种单例写法就无法使用了。
 *
 * 优点：没有加锁，执行效率会提高。
 * 缺点：类加载时就初始化，浪费内存。
 * Created by matt on 5/8/16.
 */
public class SingleObject3 {
	private static SingleObject3 instance = new SingleObject3();

	private SingleObject3() {
	}

	public static SingleObject3 getInstance() {
		return instance;
	}

	public void showMessage() {
		System.out.println("3: Hello World!");
	}
}
