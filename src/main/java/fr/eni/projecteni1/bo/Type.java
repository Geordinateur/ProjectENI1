package fr.eni.projecteni1.bo;

public class Type {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    private String libelle;

    public Type(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Type() {}
}
