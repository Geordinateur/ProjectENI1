package fr.eni.projecteni1.bo.Produit;

public interface Type {
  int getId();
  void setId(int id);
  EnumProductType getLibelle();
  void setLibelle(EnumProductType libelle);
}