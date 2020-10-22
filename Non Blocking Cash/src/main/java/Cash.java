import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Cash {
    private final ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(Base base) {
        map.put(id.incrementAndGet(), base);
    }

    public void update(Base base) throws OptimisticException {
        if (map.containsKey(base.id)) {
            map.computeIfPresent(base.id, (key, value) -> {
                value.version++;
                return value;
            });
        } else {
            throw new OptimisticException("Mismatch version!");
        }
    }

    public void delete(Base base) {
        map.remove(base.id);
    }
}
