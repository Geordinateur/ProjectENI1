package fr.eni.projecteni1.repository;


import fr.eni.projecteni1.bo.Commande.Commande;

import java.sql.SQLException;

public interface CommandeInterface {
  public int saveOrder(Commande commande) throws SQLException;
  public int deleteOrder(Integer id) throws SQLException;

 /* public int getOrderById(Commande commande) throws SQLException;*/

}
