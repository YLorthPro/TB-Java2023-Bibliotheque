import entities.Bibliotheque;
import entities.UserRole;
import entities.Utilisateur;
import interfaces.AdminInterface;
import interfaces.Tools;
import interfaces.UserInterface;

import java.util.Scanner;

public class Session {

    public static void run(){

        Bibliotheque bibliotheque = new Bibliotheque();

        Scanner sc = new Scanner(System.in);
        Utilisateur connectedUser;
        boolean repeat = true;

        do{
            System.out.println("Veuillez rentrer votre login:");
            String login = sc.nextLine();

            System.out.println("Veuillez rentrer votre mot de passe:");
            String password = sc.nextLine();

            connectedUser = bibliotheque.getConnectedUser(login, password);

            if(connectedUser == null){
                System.out.println("Voulez vous réessayer?");
                repeat = Tools.ouiOuNon();
            } else
                repeat = false;

        }while(repeat);

        if(connectedUser == null){
            System.out.println("Au revoir");
        } else if (UserRole.USER == connectedUser.getRole()) {
            UserInterface ui = new UserInterface(bibliotheque,connectedUser);
            ui.session();
        } else if (UserRole.ADMIN == connectedUser.getRole()) {
            AdminInterface ai = new AdminInterface(bibliotheque, connectedUser);
            ai.session();
        }


    }
}
