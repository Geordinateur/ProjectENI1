package fr.eni.projecteni1.controller;

import fr.eni.projecteni1.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @GetMapping({"/Produits"})
    @CrossOrigin
    public String getProduit() { return this.produitService.getProduitService(); }
}
