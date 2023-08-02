package fr.eni.projecteni1.repository;

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
import java.util.List;

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
      String sql = "INSERT INTO orders (heure_preparation) VALUES (?)";

       jdbcTemplate.update(new PreparedStatementCreator() {
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

      String insertDetail = "INSERT INTO detail_orders (fk_id_order,fk_id_product, quantity) VALUES (?,?,?)";
       jdbcTemplate.batchUpdate(insertDetail, commande.getDetailOrder(), commande.getDetailOrder().size(), new ParameterizedPreparedStatementSetter<DetailOrderDTO>() {
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
      String sql = "DELETE FROM orders WHERE id = ? ";
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
  @Override
  public int updateOrder(CommandeDTO commande, CommandeDTO commandeUpdate) {
    try {
      String sql = "UPDATE orders SET heure_preparation = ?, status = ? WHERE id = ?";
      int rowAffected = jdbcTemplate.update(sql,
              commande.getHeurePreparation(),
              commandeUpdate.getStatus(),
              commande.getId());
      if(rowAffected == 0) {
        System.out.println(("Aucune ligne mise à jour."));
      } else {
        System.out.println(commande.toString());
        System.out.println(commande.getStatus());
        System.out.println(rowAffected + "commande mise à jour");
      }
      return rowAffected;
    } catch(Exception error) {
      error.printStackTrace();
      throw error;
    }
  }

  public static class OrderRowMapper implements RowMapper<CommandeDTO>{
    @Override
    public CommandeDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
      CommandeDTO order = new CommandeDTO();
      order.setId(rs.getInt("id"));
      order.setHeurePreparation(rs.getDate("heure_preparation"));
      order.setStatus(rs.getString("status"));
      return order;
    }
  }
  @Override
  public CommandeDTO getOrderById(Integer id) throws SQLException {
    String sql = "SELECT * FROM orders WHERE id = ?";
    CommandeDTO order = jdbcTemplate.queryForObject(sql, new Object[]{id}, new OrderRowMapper());
      String detailOrderSql = "SELECT * FROM detail_orders WHERE fk_id_order = ?";
      List<DetailOrderDTO> details = jdbcTemplate.query(detailOrderSql,new Object[]{order.getId()}, new DetailOrderRowMapper());
      for(DetailOrderDTO detail : details){
        detail.setCommande(order.getId());
      }
      order.setDetailOrder(details);
      return order;
  }

  public static class DetailOrderRowMapper implements RowMapper<DetailOrderDTO>{
    @Override
    public DetailOrderDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
      DetailOrderDTO detail = new DetailOrderDTO();
      detail.setId(rs.getInt("fk_id_order"));
      detail.setProduit(rs.getInt("fk_id_product"));
      detail.setQuantite(rs.getInt("quantity"));
      return detail;
    }
  }

  @Override
  public List<CommandeDTO> getOrders() throws SQLException {
    String sql = "SELECT * FROM orders";
    List<CommandeDTO> orders = jdbcTemplate.query(sql, new OrderRowMapper());
    for(CommandeDTO order : orders){
        String detailOrderSql = "SELECT * FROM detail_orders WHERE fk_id_order = ?";
        List<DetailOrderDTO> details = jdbcTemplate.query(detailOrderSql,new Object[]{order.getId()}, new DetailOrderRowMapper());
        for(DetailOrderDTO detail : details){
          detail.setCommande(order.getId());
        }
        order.setDetailOrder(details);
    }
    return orders;
  }

}
