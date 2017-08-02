package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by matt on 16/8/9.
 */
public class Counter2 {
    private AtomicInteger counter = new AtomicInteger();

    public int increase() {
        return counter.incrementAndGet();
    }

    public int decrease() {
        return counter.decrementAndGet();
    }
}
