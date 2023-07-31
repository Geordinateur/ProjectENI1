package fr.eni.projecteni1.bo.Produit;

public class Produit {
  private Integer id;
  private String nom;
  private String description;
  private Double prix;

  public Produit(Integer id, String nom, String description, Double prix) {
    this.id = id;
    this.nom = nom;
    this.description = description;
    this.prix = prix;
  }

  public Produit() {
  }

  // Getters
  public Integer getId() {
    return id;
  }

  public String getNom() {
    return nom;
  }

  public String getDescription() {
    return description;
  }

  public Double getPrix() {
    return prix;
  }

  // Setters
  public void setId(Integer id) {
    this.id = id;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPrix(Double prix) {
    this.prix = prix;
  }

  @Override
  public String toString() {
    return "Produit{" +
      "id=" + id +
      ", nom='" + nom + '\'' +
      ", description='" + description + '\'' +
      ", prix=" + prix +
      '}';
  }
}

