package dal.config;

import dal.config.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DataInit {
    public static void init(){
        String sql = """
                                
                DROP TABLE IF EXISTS emprunt;
                DROP TABLE IF EXISTS livre;
                DROP TABLE IF EXISTS utilisateur;
                                
                CREATE TABLE livre (
                    id SERIAL PRIMARY KEY,
                    titre varchar(50), 
                    auteur varchar(50), 
                    annee_publication int, 
                    isbn varchar(50)
                    );
                CREATE TABLE utilisateur (
                    id SERIAL PRIMARY KEY,
                    prenom VARCHAR(50),
                    nom VARCHAR(50),
                    login VARCHAR(6) UNIQUE,
                    password VARCHAR(50),
                    role VARCHAR(50)
                );
                CREATE TABLE emprunt (
                    id SERIAL PRIMARY KEY,
                    livre_id INT REFERENCES livre(id),
                    emprunteur_id INT REFERENCES utilisateur(id),
                    date_emprunt DATE,
                    date_retour DATE
                );
                                
                INSERT INTO livre (id, titre, auteur, annee_publication, isbn)
                VALUES (DEFAULT, 'Le Seigneur des Anneaux', 'J.R.R. Tolkien', 1954, '9782070612388'),
                (DEFAULT, 'Harry Potter à l École des Sorciers', 'J.K. Rowling', 1997, '9782070541270'),
                (DEFAULT, '1984', 'George Orwell', 1949, '9782070373588'),
                (DEFAULT, 'Le Petit Prince', 'Antoine de Saint-Exupéry', 1943, '9782010095637'),
                (DEFAULT, 'Orgueil et Préjugés', 'Jane Austen', 1813, '9782070404341'),
                (DEFAULT, 'Moby Dick', 'Herman Melville', 1851, '9782070423434'),
                (DEFAULT, 'Le Hobbit', 'J.R.R. Tolkien', 1937, '9782070612364'),
                (DEFAULT, 'Crime et Châtiment', 'Fiodor Dostoïevski', 1866, '9782070383570'),
                (DEFAULT, 'L Écume des Jours', 'Boris Vian', 1947, '9782070367983'),
                (DEFAULT, 'Les Misérables', 'Victor Hugo', 1862, '9782070412825');
                
                INSERT INTO utilisateur (id, prenom, nom, login, password, role)
                VALUES (DEFAULT, 'Yann', 'Lorthioir', 'YanLor', 'Test1234=', 'ADMIN'),
                (DEFAULT, 'Java', 'Script', 'JavScr', 'nonType', 'USER');
                
                INSERT INTO emprunt (id, livre_id, emprunteur_id, date_emprunt, date_retour)
                VALUES (DEFAULT, 1, 1, '2023-03-25','2023-09-25');
                                
                                
                """;

        System.out.println("Init...");

        try (Connection connection = ConnectionFactory.createConnection();
             Statement statement = connection.createStatement()) {
            try{

                connection.setAutoCommit(false);
                System.out.println("Connection ok...");
                statement.execute(sql);
                System.out.println("Init done!");
                connection.commit();
            }catch(SQLException e){
                connection.rollback();
                throw new RuntimeException("error",e);
            }
        }catch (SQLException e){
            throw new RuntimeException("error",e);
        }
    }
}
