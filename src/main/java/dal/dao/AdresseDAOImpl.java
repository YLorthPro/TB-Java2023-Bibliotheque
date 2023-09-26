package dal.dao;

import dal.config.ConnectionFactory;
import entities.Adresse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AdresseDAOImpl implements AdresseDAO{
    @Override
    public boolean create(Adresse entity) {
        String sql = "INSERT INTO adresse VALUES (DEFAULT, ?, ?, ?, ?)";

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,entity.getNumero());
            preparedStatement.setString(2,entity.getRue());
            preparedStatement.setString(3,entity.getCodePostal());
            preparedStatement.setString(4,entity.getVille());

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
    public Optional<Adresse> getOne(Long id) {
        String sql = "SELECT * from Adresse WHERE id = ?";

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1,id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next())
                return Optional.of(new Adresse(rs.getLong("id"), rs.getString("numero"), rs.getString("rue"), rs.getString("codePostal"),rs.getString("ville")));
            else
                return Optional.empty();



        }catch (SQLException ex){
            throw new RuntimeException("User error: ",ex);
        }
    }

    @Override
    public Optional<Adresse> exists(Adresse adresse) {
        String sql = "SELECT * from Adresse WHERE numero = ? AND rue = ? AND codePostal = ? AND  ville = ?";

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, adresse.getNumero());
            preparedStatement.setString(2, adresse.getRue());
            preparedStatement.setString(3, adresse.getCodePostal());
            preparedStatement.setString(4, adresse.getVille());

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next())
                return Optional.of(new Adresse(rs.getLong("id"), rs.getString("numero"), rs.getString("rue"), rs.getString("codePostal"),rs.getString("ville")));
            else
                return Optional.empty();

        }catch (SQLException ex){
            ex.printStackTrace();
            return Optional.empty();
        }
    }
}
