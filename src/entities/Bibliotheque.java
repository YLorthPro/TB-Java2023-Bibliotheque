package entities;

import java.time.LocalDate;
import java.util.*;

public class Bibliotheque {
    private final Set<Livre> livreSet = new HashSet<>();
    private final Map<String, Utilisateur> utilisateurMap = new HashMap<>();
    private final Map<String, Emprunt> empruntMap = new HashMap<>();

    //init

    public Bibliotheque() {
        Utilisateur u1 = new Utilisateur("Yann", "Lorthioir", "Test1234=", UserRole.ADMIN);
        Utilisateur u2 = new Utilisateur("Java","Script","nonType",UserRole.USER);
        utilisateurMap.put(u1.getLogin(),u1);
        utilisateurMap.put(u2.getLogin(),u2);
    }

    public Utilisateur getConnectedUser(String login, String password){
        if(utilisateurMap.containsKey(login)&&utilisateurMap.get(login).login(login,password))
            return utilisateurMap.get(login);
        else return null;
    }

    public boolean ajouterLivre(Livre livre){
        return livreSet.add(livre);
    }

    public boolean supprimerLivre(Livre livre){
        return livreSet.remove(livre);
    }

    public boolean updateLivre(String isbnToUpdate, String titre, String auteur, int anneePublication){
        Livre toUpdate = getOne(isbnToUpdate);

        if(toUpdate == null)
            return false;

        livreSet.remove(toUpdate);
        toUpdate.setTitre(titre);
        toUpdate.setAuteur(auteur);
        toUpdate.setAnneePublication(anneePublication);
        return ajouterLivre(toUpdate);
    }

    public Livre getOne (String isbn){
        return livreSet.stream()
                .filter(e->e.getIsbn().equals(isbn))
                .findFirst().get();
    }

    public List<Livre> getAll(){
        return livreSet.stream().toList();
    }

    public void afficherInfo(String isbn){
        System.out.println(getOne(isbn));
    }

    public boolean emprunterLivre(String isbn, Utilisateur emprunteur){
        if(empruntMap.containsKey(isbn)&&empruntMap.get(isbn).getDateRetour()==null)
            return false;
        else{
            empruntMap.remove(isbn);
            empruntMap.put(isbn, new Emprunt(getOne(isbn), emprunteur, LocalDate.now()));
            return true;
        }
    }

    public boolean retournerLivre(String isbn){
        if(empruntMap.containsKey(isbn)){
            empruntMap.get(isbn).setDateRetour(LocalDate.now());
            return true;
        }
        else
            return false;
    }

    public List<Livre> listeLivresDispo(){
        return livreSet.stream()
                .filter(livre-> !empruntMap.containsKey(livre.getIsbn()) || !(empruntMap.get(livre.getIsbn()).getDateRetour()==null))
                .toList();
    }
}
