package factorypattern;

public class FactoryPatternDemo {
	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		// ��ȡ Circle �Ķ��󣬲��������� draw ����
		Shape shape1 = shapeFactory.getShape("CIRCLE");
		// ���� Circle �� draw ����
		shape1.draw();
		// ��ȡ Rectangle �Ķ��󣬲��������� draw ����
		Shape shape2 = shapeFactory.getShape("RECTANGLE");
		// ���� Rectangle �� draw ����
		shape2.draw();
		// ��ȡ Square �Ķ��󣬲��������� draw ����
		Shape shape3 = shapeFactory.getShape("SQUARE");
		// ���� Square �� draw ����
		shape3.draw();
	}
}
