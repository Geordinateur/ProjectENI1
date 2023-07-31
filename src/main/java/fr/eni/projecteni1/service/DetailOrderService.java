package fr.eni.projecteni1.service;

import fr.eni.projecteni1.bo.Produit.Produit;
import fr.eni.projecteni1.repository.DetailOrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DetailOrderService {
  @Autowired
  private DetailOrderImpl detailCommande;

  public int createDeatilOrder (Produit produit, int quantite)throws SQLException {
    return this.detailCommande.createDetailOrder(produit, quantite);
  }

  public Boolean deleteDetailOrder (Integer id) throws SQLException{
    try{
      int result = this.detailCommande.deleteDetailOrder(id);
      if(result > 0){
        return true;
      }
      return false;
    }catch(SQLException error ){
      System.out.println("Erreur SQL : " + error.getMessage());
      System.out.println("Code d'état SQL : " + error.getSQLState());
      System.out.println("Code d'erreur du fournisseur : " + error.getErrorCode());
      error.printStackTrace();
      return false;
    }
  }

}
