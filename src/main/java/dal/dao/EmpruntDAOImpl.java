package dal.dao;

import dal.config.ConnectionFactory;
import entities.Emprunt;
import entities.Livre;
import entities.UserRole;
import entities.Utilisateur;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EmpruntDAOImpl implements EmpruntDAO{

    @Override
    public boolean create(Emprunt entity) {
        String sql = "INSERT INTO emprunt VALUES (DEFAULT, ?, ?, ?, ?)";

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1,entity.getLivre().getId());
            preparedStatement.setLong(2,entity.getEmprunteur().getId());
            preparedStatement.setDate(3, Date.valueOf(entity.getDateEmprunt()));
            preparedStatement.setDate(4,null);

            if(preparedStatement.executeUpdate()>0)
                return true;
            else
                return false;

        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Livre> getAllDispo() {

        List<Livre> livres = new ArrayList<>();

        String sql = "SELECT * FROM livre l LEFT JOIN emprunt e ON l.id = e.livre_id WHERE e.livre_id IS NULL OR e.date_retour IS NOT NULL AND l.id NOT IN (SELECT livre_id FROM emprunt WHERE date_retour IS NULL);";

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Livre livre = new Livre(rs.getLong("id"), rs.getString("titre"),rs.getString("auteur"),rs.getInt("annee_publication"),rs.getString("isbn"));
                livres.add(livre);
            }



        }catch (SQLException ex){
            throw new RuntimeException("Book error: ",ex);
        }

        return livres;
    }

    @Override
    public Optional<Emprunt> getOne(String isbn) {

        String sql = "SELECT * from livre l JOIN emprunt e ON l.id = e.livre_id JOIN utilisateur u ON e.emprunteur_id = u.id WHERE isbn = ? AND date_retour IS NULL";

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,isbn);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Livre livre = new Livre(rs.getLong("livre_id"), rs.getString("titre"), rs.getString("auteur"), rs.getInt("annee_publication"), rs.getString("isbn"));
                Utilisateur utilisateur = new Utilisateur(rs.getLong("emprunteur_id"), rs.getString("prenom"),rs.getString("nom"),null, UserRole.valueOf(rs.getString("role")));
                return Optional.of(new Emprunt(rs.getLong("id"), livre, utilisateur, rs.getDate("date_emprunt").toLocalDate(),null));
            }else
                return Optional.empty();



        }catch (SQLException ex){
            throw new RuntimeException("Book error: ",ex);
        }
    }

    @Override
    public boolean update(String isbn, LocalDate dateRetour) {

        String sql = "UPDATE emprunt SET date_retour = ? WHERE id=?";

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setDate(1, Date.valueOf(dateRetour));
            preparedStatement.setLong(2, getOne(isbn).get().getId());

            if(preparedStatement.executeUpdate()>0)
                return true;
            else
                return false;

        }catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

}
