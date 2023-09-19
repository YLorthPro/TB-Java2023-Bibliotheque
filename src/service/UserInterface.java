package service;

import entities.Bibliotheque;
import entities.Livre;
import entities.Utilisateur;

import java.util.List;
import java.util.Scanner;

public class UserInterface implements fonctionnalitesBase {

    private final Bibliotheque bibliotheque;
    private final Utilisateur user;

    public UserInterface(Bibliotheque bibliotheque,Utilisateur user) {
        this.bibliotheque = bibliotheque;
        this.user = user;
    }

    @Override
    public void rechercherLivre() {
        List<Livre> liste = bibliotheque.getAll();
        for (int i = 0; i < liste.size(); i++) {
            System.out.println(i + " - " + liste.get(i));
        }
        System.out.println("Quel livre voulez vous?");
        System.out.println(bibliotheque.getOne(liste.get(Tools.demanderNB(0,liste.size())).getIsbn()));
    }

    @Override
    public void emprunterLivre() {
        List<Livre> liste = bibliotheque.getAll();
        for (int i = 0; i < liste.size(); i++) {
            System.out.println(i + " - " + liste.get(i));
        }
        System.out.println("Quel livre voulez vous?");
        if(bibliotheque.emprunterLivre(bibliotheque.getOne(liste.get(Tools.demanderNB(0,liste.size())).getIsbn()).getIsbn(),user)){
            System.out.println("Emprunt effectué");
        } else
            System.out.println("Emprunt impossible");
    }

    @Override
    public void retournerLivre() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez rentrer l'ISBN du livre à retourner");
        if (bibliotheque.retournerLivre(sc.nextLine()))
            System.out.println("Retour effectué avec succès");
        else
            System.out.println("Retour impossible pour cet ISBN");
    }

    @Override
    public void livresDispo() {
        bibliotheque.listeLivresDispo().forEach(Livre::toString);
    }
}
