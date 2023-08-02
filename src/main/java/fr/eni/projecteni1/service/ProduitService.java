package fr.eni.projecteni1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.eni.projecteni1.bo.Produit;

public interface ProduitService {
    public List<Produit> getProduits();
    public void saveProduit(Produit produit);
    public int deleteProduit(Produit produit);
    public int updateProduit(Integer idProduit, Produit produit);

    //private ProduitRepository produitRepository;
    //public String getProduitService() { return this.produitRepository.getProduit(); }
}
