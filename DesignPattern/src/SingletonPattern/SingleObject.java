package SingletonPattern;

/**
 * SingleObject
 * 
 * @author matt
 *
 */
public class SingleObject {
	// build an object of SingleObject
	private static SingleObject instance = new SingleObject();

	// set the structure method to private to avoid to be called
	private SingleObject() {
	}

	// get the the single object
	public static SingleObject getInstance() {
		return instance;
	}

	public void showMessage() {
		System.out.println("Hello World!");
	}
}
