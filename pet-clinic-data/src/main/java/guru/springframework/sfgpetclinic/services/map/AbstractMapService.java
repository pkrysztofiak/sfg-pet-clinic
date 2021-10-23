package guru.springframework.sfgpetclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, I> {

    Map<I, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(I id) {
        return map.get(id);
    }

    T save(I id, T t) {
        map.put(id, t);
        return t;
    }

    void deleteById(I id) {
        map.remove(id);
    }

    void delete(T t) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(t));
    }
}
