package fr.eni.projecteni1.bo.DetailCommande;

import fr.eni.projecteni1.bo.Commande.CommandeStatus;
import fr.eni.projecteni1.bo.Produit.Produit;

public class DetailCommande {
  private Integer id;
  private Produit produit;

  private Integer quantité;


  public DetailCommande(Produit produit, Integer quantité) {
    this.produit = produit;
    this.quantité = quantité;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Produit getProduit() {
    return produit;
  }

  public void setProduit(Produit produit) {
    this.produit = produit;
  }

  public Integer getQuantité() {
    return quantité;
  }

  public void setQuantité(Integer quantité) {
    this.quantité = quantité;
  }

  @Override
  public String toString() {
    return "DetailCommande{" +
      "id=" + id +
      ", produit=" + produit +
      ", quantité=" + quantité +
      '}';
  }
}
