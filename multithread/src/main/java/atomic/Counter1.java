package atomic;

/**
 * Created by matt on 16/8/9.
 */
public class Counter1 {
    private int counter = 0;

    public int increase() {
        synchronized (this) {
            counter = counter + 1;
            return counter;
        }
    }

    public int decrease() {
        synchronized (this) {
            counter = counter - 1;
            return counter;
        }
    }
}
