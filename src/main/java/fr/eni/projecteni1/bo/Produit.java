package fr.eni.projecteni1.bo;

public class Produit {

  private String name;
  private Float prix;
  private int id;
  private int idType;

  public Produit() {}

  public Produit(String name, Float prix,  int idType) {
    this.name = name;
    this.prix = prix;
    this.idType = idType;
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
}
