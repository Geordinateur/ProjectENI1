package fr.eni.projecteni1.bo.DetailCommande;

import fr.eni.projecteni1.bo.Produit.Produit;

public class DetailOrder {
  private Integer id;
  private Produit produit;
  private Integer quantity;

  public DetailOrder( Produit produit, Integer quantity) {
    this.produit = produit;
    this.quantity = quantity;
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

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "DetailOrder{" +
      "id=" + id +
      ", produit=" + produit +
      ", quantity=" + quantity +
      '}';
  }
}
