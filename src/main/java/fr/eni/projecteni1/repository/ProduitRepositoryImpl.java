package fr.eni.projecteni1.repository;

import fr.eni.projecteni1.bo.Produit;
import fr.eni.projecteni1.bo.Type;
import fr.eni.projecteni1.controller.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProduitRepositoryImpl implements ProduitRepository {

    private final JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    public ProduitRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Produit> getProduits() throws DataAccessException {
        try {
            String sql = "SELECT p.id, p.name, p.detail, p.price, p.quantity, p.fk_id_type, p.enable, t.name as type_name FROM products p INNER JOIN types t ON p.fk_id_type= t.id ";
            List<Produit> results = jdbcTemplate.query(sql, new ProduitRowMapper());
            logger.info(results.size() + " produit(s) récupérer");
            return results;
        } catch(DataAccessException error) {
            logger.error(error.toString());
            throw error;
        }
    }

    @Override
    public void saveProduit(Produit produit) {
        try {
            String sql = "INSERT INTO products (name, detail, price, quantity, fk_id_type) VALUES (?, ?, ?, ?, ?)";
            int res = jdbcTemplate.update(sql, produit.getName(), produit.getDetails(), produit.getPrix(), produit.getQuantite(), produit.getIdType());
            logger.info(res + " produit ajouté(s)");
        }catch(DataAccessException error){
            logger.error(error.toString());
        }
    }

    @Override
    public int deleteProduit(Integer id) {
        try {
            String sql = "DELETE FROM products WHERE id = ? ";
            int result = jdbcTemplate.update(sql, id);
            logger.info(result + " produit supprimé(s)");
            return result;
        }catch(DataAccessException error) {
            logger.error((error.toString()));
            throw error;
        }

    }

    public int updateProduit(Integer idProduit, Produit produit) {
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ?, fk_id_type = ?, enable = ? WHERE id = ?";
        return jdbcTemplate.update(sql, produit.getName(), produit.getPrix(), produit.getQuantite(), produit.getIdType(), produit.getEnable(), produit.getId());
    }


    private static class ProduitRowMapper implements RowMapper<Produit> {
        @Override
        public Produit mapRow(ResultSet rs, int rowNum) throws SQLException {
            Produit produit = new Produit();
            produit.setId(rs.getInt("id"));
            produit.setName(rs.getString("name"));
            produit.setDetails(rs.getString("detail"));
            produit.setPrix(rs.getFloat("price"));
            produit.setQuantite((rs.getInt("quantity")));
            produit.setEnable((rs.getBoolean("enable")));
            produit.setIdType(rs.getInt("fk_id_type"));
            produit.setType(new Type(rs.getInt("fk_id_type"), rs.getString("type_name")));
            return produit;
        }
    }
}
