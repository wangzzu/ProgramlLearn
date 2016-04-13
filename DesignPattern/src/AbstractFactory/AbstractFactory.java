package AbstractFactory;

/**
 * the Abstract Factory
 * 
 * @author matt
 *
 */
public abstract class AbstractFactory {
	abstract Color getColor(String colorType);

	abstract Shape getShape(String shapeType);
}
