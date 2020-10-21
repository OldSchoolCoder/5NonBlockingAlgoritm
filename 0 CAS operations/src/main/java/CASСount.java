import java.util.concurrent.atomic.AtomicReference;

public class CASСount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int oldValue;
        int incrementValue;
        do {
            oldValue = count.get();
            incrementValue = oldValue + 1;
        } while (!count.compareAndSet(oldValue, incrementValue));
    }

    public int get() {
        return count.get();
    }
}

