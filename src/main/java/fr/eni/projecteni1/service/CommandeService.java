package fr.eni.projecteni1.service;

import fr.eni.projecteni1.bo.Commande.CommandeDTO;
import fr.eni.projecteni1.repository.CommandeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CommandeService {
  @Autowired
  private CommandeDAO commandeDAO;

  public void saveOrder(CommandeDTO commande) throws SQLException {
    try {
      this.commandeDAO.saveOrder(commande);
    } catch (SQLException error) {
      System.out.println("Erreur SQL : " + error.getMessage());
      System.out.println("Code d'état SQL : " + error.getSQLState());
      System.out.println("Code d'erreur du fournisseur : " + error.getErrorCode());
      error.printStackTrace();
      throw error;
    }
  }

  public Boolean deleteOrder(Integer id) throws SQLException {
    try {
      int result = this.commandeDAO.deleteOrder(id);
      if (result > 0) {
        return true;
      }
      return false;
    } catch (SQLException error) {
      System.out.println("Erreur SQL : " + error.getMessage());
      System.out.println("Code d'état SQL : " + error.getSQLState());
      System.out.println("Code d'erreur du fournisseur : " + error.getErrorCode());
      error.printStackTrace();
      return false;
    }
  }

  public Boolean updateOrder(CommandeDTO commande, CommandeDTO commandeUpdate) throws SQLException {
    try {
      int result = this.commandeDAO.updateOrder(commande, commandeUpdate);
      if(result > 0) {
        return true;
      }
      return false;
    } catch (SQLException error) {
      System.out.println("Erreur SQL : " + error.getMessage());
      System.out.println("Code d'état SQL : " + error.getSQLState());
      System.out.println("Code d'erreur du fournisseur : " + error.getErrorCode());
      error.printStackTrace();
      return false;
    }
  }

  public List<CommandeDTO> getOrder() throws SQLException {
    return this.commandeDAO.getOrders();
  }
  public CommandeDTO getOrderById(Integer id) throws SQLException {
    try {
      CommandeDTO order = this.commandeDAO.getOrderById(id);
      if (order == null) {
        throw new RuntimeException();
      }
      return order;

    } catch (SQLException error) {
      System.out.println("Erreur SQL : " + error.getMessage());
      System.out.println("Code d'état SQL : " + error.getSQLState());
      System.out.println("Code d'erreur du fournisseur : " + error.getErrorCode());
      error.printStackTrace();
      throw new RuntimeException("SQL erred", error);
    }
  }
}
