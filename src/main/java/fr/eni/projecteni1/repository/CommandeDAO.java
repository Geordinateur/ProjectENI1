package fr.eni.projecteni1.repository;


import fr.eni.projecteni1.bo.Commande.Commande;
import fr.eni.projecteni1.bo.Commande.CommandeDTO;

import java.sql.SQLException;
import java.util.List;

public interface CommandeDAO {
  void saveOrder(CommandeDTO commande) throws SQLException;
  int deleteOrder(Integer id) throws SQLException;
  int updateOrder(CommandeDTO commande, CommandeDTO updateCommande) throws SQLException;
  CommandeDTO getOrderById(Integer id) throws SQLException;
  List<CommandeDTO> getOrders() throws SQLException;

}
