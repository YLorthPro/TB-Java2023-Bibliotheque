package entities;

public class Utilisateur {
    private Long id;
    private String prenom;
    private String nom;
    private String login;
    private String password;
    private UserRole role;
    private Adresse adresse;


    public Utilisateur(Long id, String prenom, String nom, String login, String password, UserRole role, Adresse adresse) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.login = login;
        this.password = password;
        this.role = role;
        this.adresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public String getLogin() {
        return login;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    @Override
    public String toString() {
        return "Utilisateur: " + nom + " " +prenom;
    }


}
