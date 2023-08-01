package fr.eni.projecteni1.repository;

import fr.eni.projecteni1.bo.Commande.Commande;
import fr.eni.projecteni1.bo.DetailCommande.DetailOrder;
import fr.eni.projecteni1.bo.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.List;

@Repository
public class DetailOrderImpl implements DetailOrderDAO {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public DetailOrderImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int createDetailOrder(Commande commande ,Produit produit, Integer quantite) throws SQLException {
    if(produit == null ){
      throw new IllegalArgumentException("Product can not be null");
    }
    try{
      DetailOrder detailOrder = new DetailOrder(produit, quantite);
      int detailOrderId = saveDetailOrder(detailOrder, commande, produit);
      return detailOrderId;
    }catch(Exception error){
      error.printStackTrace();
      throw error;
    }
  }
  @Override
  public int saveDetailOrder(DetailOrder detailOrder, Commande commande, Produit produit)  {
    if(detailOrder == null){
      throw new IllegalArgumentException("DetailOrder can not be null");
    }
    try{
      KeyHolder keyHolder = new GeneratedKeyHolder();
      String sql = "INSERT INTO detailCommandes(fk_id_commande, fk_id_produit, quantite) VALUES(?,?,?)";
      int result = jdbcTemplate.update(new PreparedStatementCreator() {
        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
          PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

          ps.setInt(1, commande.getId());
          ps.setInt(2, produit.getId());
          ps.setInt(3, detailOrder.getQuantity() );
          return ps;
        }
      }, keyHolder);
      if(keyHolder.getKey()==null){
        throw new SQLException("Failed to insert DetailOrder, no ID obtained");
      }
      detailOrder.setId(keyHolder.getKey().intValue());
      return result;
    }catch(DataAccessException | SQLException error){
      error.printStackTrace();
      throw new RuntimeException(error);
    }
  }


  @Override
  public int deleteDetailOrder(Integer id) throws SQLException {
    if (id == null) {
      throw new IllegalArgumentException("ID can not be null");
    }

    try {
      String sql = "DELETE FROM detailCommandes WHERE id_detailCommande=? ";
      int rowAffected = jdbcTemplate.update(sql, id);
      if(rowAffected == 0 ){
        System.out.print("Aucune ligne effacée");
      }else{
        System.out.println(rowAffected + " ligne effacée");
      }
    return rowAffected;

    } catch (Exception error) {
      error.printStackTrace();
      throw error;
    }
  }

  @Override
  public int updateDetailOrder(Produit produit) {
    return 0;
  }


}
