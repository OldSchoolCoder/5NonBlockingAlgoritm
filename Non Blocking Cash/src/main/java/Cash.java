import java.util.concurrent.ConcurrentHashMap;

public class Cash {
    private final ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    public void add(Base base) {
        map.put(base.id, base);
    }

    public void update(Base base) throws OptimisticException {
        if (!map.containsKey(base.id)) {
            throw new OptimisticException("Mismatch version!");
        }
        map.computeIfPresent(base.id, (key, value) -> {
            value.version++;
            return value;
        });
    }

    public void delete(Base base) {
        map.remove(base.id);
    }
}
