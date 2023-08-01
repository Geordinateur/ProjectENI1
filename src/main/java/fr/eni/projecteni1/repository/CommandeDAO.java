package fr.eni.projecteni1.repository;


import fr.eni.projecteni1.bo.Commande.Commande;
import fr.eni.projecteni1.bo.Commande.CommandeDTO;

import java.sql.SQLException;

public interface CommandeDAO {
  void saveOrder(CommandeDTO commande) throws SQLException;

  int deleteOrder(Integer id) throws SQLException;

  Commande getOrderById(Integer id) throws SQLException;

}
