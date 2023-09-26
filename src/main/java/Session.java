import dal.dao.UtilisateurDAO;
import dal.dao.UtilisateurDAOImpl;
import entities.Bibliotheque;
import entities.UserRole;
import entities.Utilisateur;
import interfacesUtilisateur.AdminInterface;
import interfacesUtilisateur.Tools;
import interfacesUtilisateur.UserInterface;

import java.util.Optional;
import java.util.Scanner;

public class Session {

    public static void run(){

        Bibliotheque bibliotheque = new Bibliotheque();
        UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();

        Scanner sc = new Scanner(System.in);
        Optional<Utilisateur> connectedUser;
        boolean repeat = true;

        do{
            System.out.println("Veuillez rentrer votre login:");
            String login = sc.nextLine();

            System.out.println("Veuillez rentrer votre mot de passe:");
            String password = sc.nextLine();

            connectedUser = utilisateurDAO.login(login, password);

            if(connectedUser.isEmpty()){
                System.out.println("Voulez vous réessayer?");
                repeat = Tools.ouiOuNon();
            } else
                repeat = false;

        }while(repeat);

        if(connectedUser.isEmpty()){
            System.out.println("Au revoir");
        } else if (UserRole.USER == connectedUser.get().getRole()) {
            UserInterface ui = new UserInterface(bibliotheque,connectedUser.get());
            ui.session();
        } else if (UserRole.ADMIN == connectedUser.get().getRole()) {
            AdminInterface ai = new AdminInterface(bibliotheque, connectedUser.get());
            ai.session();
        }


    }
}
