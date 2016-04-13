package AbstractFactory;

/**
 * the Square class
 * 
 * @author matt
 *
 */
public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("Square: draw() method.");
	}
}
