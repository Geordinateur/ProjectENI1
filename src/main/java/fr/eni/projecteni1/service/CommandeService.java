package fr.eni.projecteni1.service;

import fr.eni.projecteni1.bo.Commande.Commande;
import fr.eni.projecteni1.repository.CommandeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandeService {
  @Autowired
  private CommandeInterface commandeInterface;

  public void saveCommande(Commande commande){
    this.commandeInterface.saveCommand(commande);
  }

  public void deleteCommande(Commande id_commande){
    this.commandeInterface.deleteCommand(id_commande);
  }
}
