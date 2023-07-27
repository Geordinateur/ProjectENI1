package fr.eni.projecteni1.repository;


import fr.eni.projecteni1.bo.Commande.Commande;

public interface CommandeInterface {
  public void saveCommand(Commande commande) ;
  public void deleteCommand(Commande commande);
}
