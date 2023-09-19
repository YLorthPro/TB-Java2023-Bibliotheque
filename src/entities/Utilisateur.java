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
        this.login = prenom.substring(0,3)+nom.substring(0,3);
        this.password = password;
        this.role = role;
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

    public boolean login(String login, String password){
        return (this.login.equals(login) && this.password.equals(password));
    }


}
