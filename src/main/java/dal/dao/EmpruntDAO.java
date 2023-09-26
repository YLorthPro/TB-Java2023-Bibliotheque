package dal.dao;

import entities.Emprunt;
import entities.Livre;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmpruntDAO{

    boolean create(Emprunt entity);
    List<Livre> getAllDispo();
    Optional<Emprunt> getOne(String isbn);
    boolean update(String isbn, LocalDate dateRetour);

}
