package abstractfactory;

public class ColorFactory extends AbstractFactory {

    public Color getColor(String colorType) {
        if (colorType == null)
            return null;
        if (colorType.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (colorType.equalsIgnoreCase("GREEN")) {
            return new Green();
        } else if (colorType.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }

    public Shape getShape(String shapeType) {
        return null;
    }

}
