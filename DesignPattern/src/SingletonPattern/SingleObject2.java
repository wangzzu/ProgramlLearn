package SingletonPattern;

/**
 * 懒汉式，线程安全
 * 它不高效，因为在任何时候只能有一个线程调用getInstance() 方法。
 *
 * 这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步
 *
 * Created by matt on 5/8/16.
 */
public class SingleObject2 {
	private static SingleObject2 instance;
	private SingleObject2(){
	}
	public static synchronized SingleObject2 getInstance(){
		if(instance==null){
			instance=new SingleObject2();
		}
		return instance;
	}
	public void showMessage() {
		System.out.println("2: Hello World!");
	}
}
