package volatitle;

/**
 * Created by matt on 16/8/9.
 */
public class VolatitleDemo {
    int i1;

    public void setI1(int i) {
        i1 = i;
    }

    volatile int i2;

    public void setI2(int i) {
        i2 = i;
    }

    int i3;

    public synchronized void setI3(int i) {
        i3 = 1;
    }

    public static void main(String[] args) {


    }

}
