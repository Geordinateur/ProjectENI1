package fr.eni.projecteni1.repository;

import fr.eni.projecteni1.bo.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProduitRepositoryImpl implements ProduitRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProduitRepositoryImpl(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<Produit> getProduits() {
        String sql = "SELECT * FROM TProduits";

        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
            }
        }, new ProduitRowMapper());
    }

    @Override
    public void saveProduit(Produit produit) {
       String sql = "INSERT INTO TProduits (name, prix, idType) VALUES (?, ?, ?)";
       jdbcTemplate.update(sql, produit.getName(), produit.getPrix(), produit.getIdType());
    }

    private static class ProduitRowMapper implements RowMapper<Produit> {
        @Override
        public Produit mapRow(ResultSet rs, int rowNum) throws SQLException {
            Produit produit = new Produit();
            //produit.setId(rs.getInt("id"));
            produit.setName(rs.getString("name"));
            produit.setPrix(rs.getFloat("prix"));
            produit.setIdType(rs.getInt("idType"));
            return produit;
        }
    }
}
