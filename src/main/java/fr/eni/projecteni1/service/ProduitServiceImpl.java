package fr.eni.projecteni1.service;

import fr.eni.projecteni1.bo.Produit;
import fr.eni.projecteni1.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {
    private ProduitRepository produitRepo;

    public ProduitServiceImpl(ProduitRepository produitRepo) { this.produitRepo = produitRepo; }

    public List<Produit> getProduits() {
        return this.produitRepo.getProduits();
    }

    @Override
    public void saveProduit(Produit produit) {
        this.produitRepo.saveProduit(produit);
    }
}
