import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASСount<T> {
    @GuardedBy("this")
    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        count.set(count.incrementAndGet());
    }

    public int get() {
        return count.get();
    }
}
