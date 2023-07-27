package fr.eni.projecteni1.controller;

import fr.eni.projecteni1.bo.Produit;
import fr.eni.projecteni1.repository.ProduitRepository;
import fr.eni.projecteni1.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @GetMapping({"/Produits"})
    @CrossOrigin
    public List<Produit> getProduit() {
        return this.produitService.getProduits();
    }

    @PostMapping({"/SaveProduit"})
    @CrossOrigin
    public String ajouterProduit(Produit produit) {
        this.produitService.saveProduit(produit);
        return "redirect:/Produits";
    }

}
