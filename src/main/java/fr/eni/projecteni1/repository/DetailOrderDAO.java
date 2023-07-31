package fr.eni.projecteni1.repository;

import fr.eni.projecteni1.bo.DetailCommande.DetailOrder;
import fr.eni.projecteni1.bo.Produit.Produit;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public interface DetailOrderDAO {
  int createDetailOrder(Produit produits, int quantite) throws SQLException;
  int deleteDetailOrder(Integer id)throws SQLException;
  int updateDetailOrder(Produit produit)throws SQLException;

  int saveDetailOrder(DetailOrder detailOrder) throws SQLException;
}
