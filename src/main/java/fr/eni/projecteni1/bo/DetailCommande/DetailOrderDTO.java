package fr.eni.projecteni1.bo.DetailCommande;

import fr.eni.projecteni1.bo.Commande.Commande;

public class DetailOrderDTO {
  private int id;

  private Integer commande;
  private int produit;
  private Integer quantite;

  public DetailOrderDTO() {
  }
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Integer getCommande() {
    return commande;
  }

  public void setCommande(Integer commande) {
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
