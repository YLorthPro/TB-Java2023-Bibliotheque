package dal.dao;

import entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurDAO {

    boolean create(Utilisateur entity);
    Optional<Utilisateur> getOne(Long id);
    Optional<Utilisateur> login(String login, String password);
}
