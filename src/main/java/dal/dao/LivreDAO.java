package dal.dao;

import entities.Livre;

import java.util.List;
import java.util.Optional;

public interface LivreDAO{
    boolean create(Livre entity);
    List<Livre> getAll();
    boolean update(Long id, Livre entity);
    boolean delete(Long id);
    Optional<Livre> getOne(String isbn);
}
