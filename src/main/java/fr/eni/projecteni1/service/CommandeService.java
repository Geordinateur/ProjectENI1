package fr.eni.projecteni1.service;

import fr.eni.projecteni1.bo.Commande.Commande;
import fr.eni.projecteni1.repository.CommandeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class CommandeService {
  @Autowired
  private CommandeInterface commandeInterface;

  public Boolean saveOrder(Commande commande) throws SQLException{
    try{
      int result = this.commandeInterface.saveOrder(commande);
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
      int result = this.commandeInterface.deleteOrder(id);
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
}
