package fr.eni.projecteni1.repository;

import fr.eni.projecteni1.bo.Commande.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class CommandeInterfaceImpl implements CommandeInterface {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public CommandeInterfaceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  @Override
  public void saveCommand(Commande commande) {
    if (commande.getHeurePreparation() == null) {
      throw new IllegalArgumentException("heurePreparation cannot be null");
    }
    try {
      KeyHolder keyHolder = new GeneratedKeyHolder();
      String sql = "INSERT INTO commande (heurePreparation) VALUES (?)";

      jdbcTemplate.update(new PreparedStatementCreator() {
        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
          PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
          ps.setTimestamp(1, new Timestamp(commande.getHeurePreparation().getTime()));
          return ps;
        }
      }, keyHolder);
      // Check if key was returned
      if (keyHolder.getKey() == null) {
        throw new SQLException("Failed to insert Commande, no ID obtained.");
      }
      // Return the generated key
      commande.setId(keyHolder.getKey().intValue());

    } catch (DataAccessException | SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Override
  public void deleteCommand(Commande id_commande) {
    if (id_commande.getId() == null) {
      throw new IllegalArgumentException("Commande null");
    }
    try {
      String sql = "DELETE FROM commande where id = ? ";
      int rowAffected = jdbcTemplate.update(sql, id_commande.getId());
      if (rowAffected == 0) {
        System.out.println(" Aucune ligne effacer ");
      } else {
        System.out.println(rowAffected + " ligné effacé");
      }

    } catch (Exception error) {
      error.printStackTrace();
      throw error;
    }
  }
}
