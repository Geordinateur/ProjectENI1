package fr.eni.projecteni1.repository;

import java.util.List;

import fr.eni.projecteni1.bo.Produit;

public interface ProduitRepository {
    public List<Produit> getProduits();
    public void saveProduit(Produit produit);
    public int deleteProduit(Produit produit);
    public int updateProduit(Produit produit);

}
