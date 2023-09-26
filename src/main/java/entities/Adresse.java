package entities;

public class Adresse {
    private Long id;
    private String numero;
    private String rue;
    private String codePostal;
    private String ville;

    public Adresse(Long id, String numero, String rue, String codePostal, String ville) {
        this.id = id;
        this.numero = numero;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public String getRue() {
        return rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }
}
