package interfacesUtilisateur;

import entities.Bibliotheque;
import entities.Livre;
import entities.Utilisateur;

import java.util.List;

public class UserInterface implements fonctionnalitesBase {

    final Bibliotheque bibliotheque;
    final Utilisateur user;

    public UserInterface(Bibliotheque bibliotheque,Utilisateur user) {
        this.bibliotheque = bibliotheque;
        this.user = user;
    }

    public void session(){

        boolean repeat = true;

        do{

            String choix = """
                Que voulez vous faire?
                1. Informations sur un livre
                2. Voir les livres disponibles
                3. Emprunter un livre
                4. Retourner un livre
                5. Quitter
                """;

            System.out.print(choix);
            switch (Tools.demanderNB(1,5)){
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
    public void rechercherLivre() {
        List<Livre> liste = bibliotheque.getAll();
        for (int i = 0; i < liste.size(); i++) {
            System.out.println(i + " - " + liste.get(i));
        }
        if(liste.isEmpty())
            System.out.println("Pas de livres");
        else{
            System.out.println("Quel livre voulez vous?");
            bibliotheque.afficherInfo(liste.get(Tools.demanderNB(0,liste.size()-1)).getIsbn());
        }
    }

    @Override
    public void emprunterLivre() {
        if(!bibliotheque.listeLivresDispo().isEmpty()){
            List<Livre> liste = bibliotheque.listeLivresDispo();
            for (int i = 0; i < liste.size(); i++) {
                System.out.println(i + " - " + liste.get(i));
            }
            System.out.println("Quel livre voulez vous?");
            if(bibliotheque.emprunterLivre(bibliotheque.getOne(liste.get(Tools.demanderNB(0,liste.size()-1)).getIsbn()).getIsbn(),user.getId())){
                System.out.println("Emprunt effectué");
            } else
                System.out.println("Emprunt impossible");
        }else
            System.out.println("Pas de livres");
    }

    @Override
    public void retournerLivre() {
        System.out.println("Veuillez rentrer l'ISBN du livre à retourner");
        if (bibliotheque.retournerLivre(Tools.demanderISBN()))
            System.out.println("Retour effectué avec succès");
        else
            System.out.println("Retour impossible pour cet ISBN");
    }

    @Override
    public void livresDispo() {
        if(bibliotheque.listeLivresDispo().isEmpty())
            System.out.println("Pas de livre disponible");
        bibliotheque.listeLivresDispo().forEach(System.out::println);
    }
}
