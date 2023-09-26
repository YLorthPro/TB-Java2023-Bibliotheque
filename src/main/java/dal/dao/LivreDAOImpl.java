package dal.dao;

import dal.config.ConnectionFactory;
import entities.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivreDAOImpl implements LivreDAO{
    @Override
    public boolean create(Livre entity) {
        String sql = "INSERT INTO livre VALUES (DEFAULT, ?, ?, ?, ?)";

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,entity.getTitre());
            preparedStatement.setString(2,entity.getAuteur());
            preparedStatement.setInt(3,entity.getAnneePublication());
            preparedStatement.setString(4,entity.getIsbn());

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
    public List<Livre> getAll() {

        List<Livre> livres = new ArrayList<>();

        String sql = "SELECT * from livre";

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
    public boolean update(Long id, Livre entity) {

        String sql = "UPDATE livre SET titre=?, auteur=?, annee_publication=?,isbn=? WHERE id=?";

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,entity.getTitre());
            preparedStatement.setString(2,entity.getAuteur());
            preparedStatement.setInt(3,entity.getAnneePublication());
            preparedStatement.setString(4,entity.getIsbn());
            preparedStatement.setLong(5,id);

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
    public boolean delete(Long id) {
        String sql = "DELETE FROM livre WHERE id=?";

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1,id);

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
    public Optional<Livre> getOne(String isbn) {
        String sql = "SELECT * from livre WHERE isbn = ?";

        try(Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,isbn);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next())
                return Optional.of(new Livre(rs.getLong("id"), rs.getString("titre"),rs.getString("auteur"),rs.getInt("annee_publication"),rs.getString("isbn"))) ;
            else
                return Optional.empty();



        }catch (SQLException ex){
            throw new RuntimeException("Book error: ",ex);
        }
    }
}
