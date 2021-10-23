package guru.springframework.sfgpetclinic.services;

import java.util.Set;

public interface CrudService<T, I> {

    Set<T> findAll();

    T findById(I id);

    T save(T t);

    void delete(T t);

    void deleteById(I id);
}
