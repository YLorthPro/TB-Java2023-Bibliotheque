package interfacesUtilisateur;

import entities.Bibliotheque;
import entities.Livre;
import entities.Utilisateur;

import java.util.List;
import java.util.Scanner;

public final class AdminInterface extends UserInterface implements fonctionalitesAdmin{

    public AdminInterface(Bibliotheque bibliotheque, Utilisateur user) {
        super(bibliotheque, user);
    }

    @Override
    public void session() {
        boolean repeat = true;

        do{

            String choix = """
                Que voulez vous faire?
                1. Informations sur un livre
                2. Voir les livres disponibles
                3. Emprunter un livre
                4. Retourner un livre
                5. Ajouter un livre
                6. Supprimer un livre
                7. Modifier un livre
                8. Quitter
                """;

            System.out.print(choix);
            switch (Tools.demanderNB(1,8)){
                case 1:
                    rechercherLivre();
                    break;

                case 2:
                    livresDispo();
                    break;

                case 3:
                    emprunterLivre();
                    break;

                case 4:
                    retournerLivre();
                    break;

                case 5:
                    ajouterLivre();
                    break;

                case 6:
                    retirerLivre();
                    break;

                case 7:
                    modifierLivre();
                    break;

                default:
                    repeat = false;
                    break;

            }

            if(repeat){
                System.out.println("Voulez faire une autre action?");
                repeat= Tools.ouiOuNon();
            }
        }while (repeat);

    }

    @Override
    public void ajouterLivre() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est le nom du livre?");
        String nom = sc.nextLine();
        System.out.println("Quel est son auteur?");
        String auteur = sc.nextLine();
        System.out.println("Quel est son année de publication?");
        int anneePublication = Tools.demanderNB(0,3000);
        System.out.println("Quel est sont ISBN?");
        String isbn = Tools.demanderISBN();
        bibliotheque.ajouterLivre(new Livre(0L,nom,auteur,anneePublication,isbn));
    }

    @Override
    public void retirerLivre() {
        if(!bibliotheque.getAll().isEmpty()){
            List<Livre> liste = bibliotheque.getAll();
            for (int i = 0; i < liste.size(); i++) {
                System.out.println(i + " - " + liste.get(i));
            }
            System.out.println("Quel livre voulez vous retirer?");
            if(bibliotheque.supprimerLivre(liste.get(Tools.demanderNB(0,liste.size()-1))))
                System.out.println("Livre supprimé");
            else
                System.out.println("Suppression impossible");
        } else
            System.out.println("Pas de livres");

    }

    @Override
    public void modifierLivre() {
        if(!bibliotheque.getAll().isEmpty()){
            Scanner sc = new Scanner(System.in);

            List<Livre> liste = bibliotheque.getAll();
            for (int i = 0; i < liste.size(); i++) {
                System.out.println(i + " - " + liste.get(i));
            }
            System.out.println("Quel livre voulez vous modifer?");
            int index = Tools.demanderNB(0,liste.size()-1);
            System.out.println("Quel est le nouveau titre?");
            String titre = sc.nextLine();
            System.out.println("Quel est son auteur?");
            String auteur = sc.nextLine();
            System.out.println("Quelle est l'année de publication?");
            int anneePublication = Tools.demanderNB(0,3000);

            if(bibliotheque.updateLivre(liste.get(index).getIsbn(), titre, auteur, anneePublication))
                System.out.println("Livre modifié");
            else
                System.out.println("Modification impossible");
        }else
            System.out.println("Pas de livres");

    }
}
