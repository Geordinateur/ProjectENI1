package fr.eni.projecteni1.bo.Produit;

public class Produit implements Type{

  private String name;
  private Float prix;
  private int id;
  private EnumProductType libelle;



  public Produit(String name, Float prix,  EnumProductType libelle) {
    this.name = name;
    this.prix = prix;
    this.libelle = libelle;
  }

  public Produit() {
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

  @Override
  public String toString() {
    return "Produit{" +
      "name='" + name + '\'' +
      ", prix=" + prix +
      ", id=" + id +
      ", libelle=" + libelle +
      '}';
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public void setId(int id) {
  this.id=id;
  }

  @Override
  public EnumProductType getLibelle() {
   return this.libelle;
  }

  @Override
  public void setLibelle(EnumProductType libelle) {
  this.libelle=libelle;
  }
}
