package fr.eni.projecteni1.service;

import fr.eni.projecteni1.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;
    public String getProduitService() { return this.produitRepository.getProduit(); }
}
