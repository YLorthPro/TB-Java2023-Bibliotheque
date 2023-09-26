package entities;

public class Utilisateur {
    private Long id;
    private String prenom;
    private String nom;
    private String login;
    private String password;
    private UserRole role;


    public Utilisateur(Long id, String prenom, String nom, String password, UserRole role) {
        this.id= id;
        this.prenom = prenom;
        this.nom = nom;
        this.login = prenom.substring(0,3)+nom.substring(0,3);
        this.password = password;
        this.role = role;
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

    @Override
    public String toString() {
        return "Utilisateur: " + nom + " " +prenom;
    }


}
