package fr.eni.projecteni1.bo.DetailCommande;

import fr.eni.projecteni1.bo.Commande.CommandeStatus;
import fr.eni.projecteni1.bo.Produit.Produit;

public class DetailCommandeDTO {

  private Integer idLigne;
  private Produit produit;


  public DetailCommandeDTO() {
  }

  public DetailCommandeDTO(Integer idLigne, Produit produits) {
    this.idLigne = idLigne;
    this.produit = produit;
  }

  public Integer getidLigne() {
    return idLigne;
  }

  public void setidLigne(Integer idLigne) {
    this.idLigne = idLigne;
  }

  public Produit getProduit() {
    return produit;
  }

  public void setProduits(Produit produit) {
    this.produit = produit;
  }



  @Override
  public String toString() {
    return "DetailCommandeDTO{" +
      ", produits=" + produit +
      '}';
  }
}
