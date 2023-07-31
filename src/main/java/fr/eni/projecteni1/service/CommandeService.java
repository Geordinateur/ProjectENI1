package fr.eni.projecteni1.service;

import fr.eni.projecteni1.repository.CommandeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class CommandeService {
  @Autowired
  private CommandeDAO commandeDAO;

  public Boolean saveOrder(fr.eni.projecteni1.bo.Commande.Commande commande) throws SQLException{
    try{
      int result = this.commandeDAO.saveOrder(commande);
      if(result > 0){
        return true;
      }
      return false;
    }catch(SQLException error){
      System.out.println("Erreur SQL : " + error.getMessage());
      System.out.println("Code d'état SQL : " + error.getSQLState());
      System.out.println("Code d'erreur du fournisseur : " + error.getErrorCode());
      error.printStackTrace();
      return false;
    }
  }

  public Boolean deleteOrder(Integer id) throws SQLException{
    try{
      int result = this.commandeDAO.deleteOrder(id);
      if(result > 0){
        return true;
      }
      return false;
    }catch(SQLException error){
      System.out.println("Erreur SQL : " + error.getMessage());
      System.out.println("Code d'état SQL : " + error.getSQLState());
      System.out.println("Code d'erreur du fournisseur : " + error.getErrorCode());
      error.printStackTrace();
      return false;
    }
  }

  public fr.eni.projecteni1.bo.Commande.Commande getOrderById(Integer id) throws SQLException{
    try{
      fr.eni.projecteni1.bo.Commande.Commande order =  this.commandeDAO.getOrderById(id);
      if(order == null){
       throw new RuntimeException();
      } return order;

    }catch(SQLException error){
      System.out.println("Erreur SQL : " + error.getMessage());
      System.out.println("Code d'état SQL : " + error.getSQLState());
      System.out.println("Code d'erreur du fournisseur : " + error.getErrorCode());
      error.printStackTrace();
      throw new RuntimeException("SQL erred" , error);
    }
  }
}
