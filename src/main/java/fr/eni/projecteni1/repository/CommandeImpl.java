package fr.eni.projecteni1.repository;

import fr.eni.projecteni1.bo.Commande.Commande;
import fr.eni.projecteni1.bo.Commande.CommandeDTO;
import fr.eni.projecteni1.bo.DetailCommande.DetailOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class CommandeImpl implements CommandeDAO {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public CommandeImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  @Override
  public void saveOrder(CommandeDTO commande) {
    if (commande.getHeurePreparation() == null) {
      throw new IllegalArgumentException("heurePreparation cannot be null");
    }
    try {
      KeyHolder keyHolder = new GeneratedKeyHolder();
      String sql = "INSERT INTO commande (heurePreparation) VALUES (?)";

      int result = jdbcTemplate.update(new PreparedStatementCreator() {
        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
          PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
          ps.setTimestamp(1, new Timestamp(commande.getHeurePreparation().getTime()));
          return ps;
        }
      }, keyHolder);

      if (keyHolder.getKey() == null) {
        throw new SQLException("Failed to insert Commande, no ID obtained.");
      }
       commande.setId(keyHolder.getKey().intValue());
      System.out.println("commande id " + keyHolder.getKey().intValue());

      String insertDetail = "INSERT INTO detailCommandes(fk_id_commande,fk_id_produit, quantite) VALUES (?,?,?)";
      int[][] detailOrder = jdbcTemplate.batchUpdate(insertDetail, commande.getDetailOrder(), commande.getDetailOrder().size(), new ParameterizedPreparedStatementSetter<DetailOrderDTO>() {
        @Override
        public void setValues(PreparedStatement ps, DetailOrderDTO ligne) throws SQLException {
          System.out.println("contenance de ligne " + ligne);
          ps.setInt(1, commande.getId());
          ps.setInt(2, ligne.getProduit());
          ps.setInt(3, ligne.getQuantite());
        }
      });
    } catch (DataAccessException | SQLException error) {
      error.printStackTrace();
      throw new RuntimeException(error);
    }
  }

  @Override
  public int deleteOrder(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("Commande null");
    }
    try {
      String sql = "DELETE FROM commande where id_commande = ? ";
      int rowAffected = jdbcTemplate.update(sql,id);
      if (rowAffected == 0) {
        System.out.println(" Aucune ligne effacer ");
      } else {
        System.out.println(rowAffected + " ligné effacé");
      }
      return rowAffected;

    } catch (Exception error) {
      error.printStackTrace();
      throw error;
    }
  }
  public static class OrderRowMapper implements RowMapper<Commande>{
    @Override
    public Commande mapRow(ResultSet rs, int rowNum) throws SQLException{
      Commande order = new Commande();
      order.setId(rs.getInt("id_commande"));
      order.setHeurePreparation(rs.getDate("heurePreparation"));
      return order;
    }
  }
  @Override
  public Commande getOrderById(Integer id) throws SQLException {
    String sql = "SELECT * FROM commande where id_commande = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{id}, new OrderRowMapper());
  }
}
