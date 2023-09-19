package entities;

public class Utilisateur {
    private String prenom;
    private String nom;
    private String login;
    private String password;
    private UserRole role;


    public Utilisateur(String prenom, String nom, String password, UserRole role) {
        this.prenom = prenom;
        this.nom = nom;
        this.login = prenom.substring(0,2)+nom.substring(0,2);
        this.password = password;
        this.role = role;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getLogin() {
        return login;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean login(String login, String password){
        return (this.login==login && this.password==password);
    }


}
