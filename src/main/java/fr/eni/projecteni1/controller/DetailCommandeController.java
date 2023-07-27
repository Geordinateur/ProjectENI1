/*
package fr.eni.projecteni1.controller;

import fr.eni.projecteni1.bo.DetailCommande.DetailCommandeDTO;
import fr.eni.projecteni1.bo.Produit.Produit;
import fr.eni.projecteni1.service.DetailCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DetailCommandeController {
  @Autowired
  private DetailCommandeService detailCommandeService;

  @GetMapping({"DetailCommande", "/"})
  @CrossOrigin
  public DetailCommandeDTO getDetailCommande(List<Produit> produits){
    return this.detailCommandeService.getDetailCommandeService(produits);
  }
}
*/
