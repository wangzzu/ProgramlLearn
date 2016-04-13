package SingletonPattern;

public class SingletonPatternDemo {
	public static void main(String[] args) {
		// Complie Error£ºthe structure method SingleObject() is set private
		// SingleObject object = new SingleObject();
		// get the the single object
		SingleObject object = SingleObject.getInstance();

		// show message
		object.showMessage();
	}
}
