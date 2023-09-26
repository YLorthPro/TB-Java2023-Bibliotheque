package dal.dao;

import entities.Adresse;

import java.util.Optional;

public interface AdresseDAO {
    boolean create(Adresse adresse);
    Optional<Adresse> getOne(Long id);
    Optional<Adresse> exists(Adresse adresse);
}
