package fr.eni.projecteni1.bo;

public class Produit {

  private String name;
  private Float prix;
  private Integer quantite;
  private int id;
  private int idType;
  private Type type;

  public Produit() {}

  public Produit(String name, Float prix,  int idType) {
    this.name = name;
    this.prix = prix;
    this.idType = idType;
  }

  public Produit(String name, Float prix, Type type) {
    this.name = name;
    this.prix = prix;
    this.type = type;
  }

  public Produit(String name, Float prix, Integer quantite, Type type) {
    this.name = name;
    this.prix = prix;
    this.quantite = quantite;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Float getPrix() {
    return prix;
  }

  public void setPrix(Float prix) {
    this.prix = prix;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
  this.id=id;
  }

  public int getIdType() {
   return this.idType;
  }

  public void setIdType(int idType) { this.idType=idType; }

  public Type getType() { return this.type; }

  public void setType(Type type) {
    this.type = type;
  }

  public Integer getQuantite() {
    return quantite;
  }

  public void setQuantite(Integer quantite) {
    this.quantite = quantite;
  }
}
