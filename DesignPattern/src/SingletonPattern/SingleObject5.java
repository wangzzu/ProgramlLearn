package SingletonPattern;

/**
 * 由于 SingletonHolder 是私有的，除了 getInstance() 之外没有办法访问它，因此它是懒汉式的；同时读取实例的时候不会进行同步，没有性能缺陷；
 *
 * Created by matt on 5/8/16.
 */
public class SingleObject5 {
	private static class SingleHolder{
		private static final SingleObject5 INSTANCE=new SingleObject5();
	}
	private SingleObject5(){}
	public static final SingleObject5 getInstance(){
		return SingleHolder.INSTANCE;
	}

	public void showMessage() {
		System.out.println("5: Hello World!");
	}
}
