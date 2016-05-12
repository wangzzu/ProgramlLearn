package SingletonPattern;

public class SingletonPatternDemo {
	public static void main(String[] args) {
		int[][] ans = new int[2][];
		ans[0] = new int[]{0, 0};
		ans[1] = new int[]{1, 1, 1};

		System.out.println(ans[0][1]+" "+ans[1][2]);

		SingleObject object = SingleObject.getInstance();
		// show message
		object.showMessage();

		SingleObject2 object2 = SingleObject2.getInstance();
		object2.showMessage();

		SingleObject3 object3 = SingleObject3.getInstance();
		object3.showMessage();

		SingleObject4 object4 = SingleObject4.getInstance();
		object4.showMessage();

		SingleObject5 object5 = SingleObject5.getInstance();
		object5.showMessage();
	}
}
