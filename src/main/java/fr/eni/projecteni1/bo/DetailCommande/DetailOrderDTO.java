package fr.eni.projecteni1.bo.DetailCommande;

import fr.eni.projecteni1.bo.Commande.Commande;
import fr.eni.projecteni1.bo.Produit;

public class DetailOrderDTO {
  private Commande commande;
  private int produit;
  private Integer quantite;

  public DetailOrderDTO() {
  }

  public Commande getCommande() {
    return commande;
  }

  public void setCommande(Commande commande) {
    this.commande = commande;
  }

  public int getProduit() {
    return produit;
  }

  public void setProduit(int produit) {
    this.produit = produit;
  }

  public Integer getQuantite() {
    return quantite;
  }

  public void setQuantite(Integer quantite) {
    this.quantite = quantite;
  }

  @Override
  public String toString() {
    return "DetailOrderDTO{" +
      "commande=" + commande +
      ", produit=" + produit +
      ", quantite=" + quantite +
      '}';
  }
}
