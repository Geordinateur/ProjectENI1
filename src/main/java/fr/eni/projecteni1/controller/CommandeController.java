package fr.eni.projecteni1.controller;

import fr.eni.projecteni1.bo.Commande.Commande;
import fr.eni.projecteni1.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandeController {

  @Autowired
  private CommandeService commandeService;

  @PostMapping("/saveCommande")
  @CrossOrigin
  public void saveCommande(@RequestBody Commande commande) {
    this.commandeService.saveCommande(commande);
  }

  @PostMapping("/deleteCommande")
  @CrossOrigin
  public void deleteCommande(@RequestBody Commande id_commande) {
    this.commandeService.deleteCommande(id_commande);
  }

}
