package dal.dao;

import dal.config.ConnectionFactory;
import entities.UserRole;
import entities.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UtilisateurDAOImpl implements UtilisateurDAO{
    @Override
    public boolean create(Utilisateur entity) {
        String sql = "INSERT INTO Utilisateur VALUES (DEFAULT, ?, ?, ?, ?, ?)";

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,entity.getPrenom());
            preparedStatement.setString(2,entity.getNom());
            preparedStatement.setString(3,entity.getLogin());
            preparedStatement.setString(4,entity.getPassword());
            preparedStatement.setString(5,entity.getRole().name());

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
    public Optional<Utilisateur> getOne(Long id) {

        String sql = "SELECT * from Utilisateur WHERE id = ?";

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1,id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next())
                return Optional.of(new Utilisateur(rs.getLong("id"), rs.getString("prenom"),null,null, UserRole.valueOf(rs.getString("role")))) ;
            else
                return Optional.empty();



        }catch (SQLException ex){
            throw new RuntimeException("User error: ",ex);
        }

    }

    @Override
    public Optional<Utilisateur> login(String login, String password) {
        String sql = """
                        SELECT id, prenom, nom, login, role
                        FROM utilisateur
                        WHERE login = ? AND password = ?;
                """;

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next())
                return Optional.of(new Utilisateur(rs.getLong("id"), rs.getString("prenom"),null,null, UserRole.valueOf(rs.getString("role")))) ;
            else
                return Optional.empty();



        }catch (SQLException ex){
            throw new RuntimeException("User error: ",ex);
        }


    }
}
