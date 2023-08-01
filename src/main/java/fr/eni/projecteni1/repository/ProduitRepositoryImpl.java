package fr.eni.projecteni1.repository;

import fr.eni.projecteni1.bo.Produit;
import fr.eni.projecteni1.bo.Type;
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
    public ProduitRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Produit> getProduits() {
        String sql = "SELECT p.id, p.nom, p.details, p.prix, p.quantite, p.idType, t.libelle as type_name FROM TProduits p INNER JOIN TTypes t ON p.idType = t.id ";

        return jdbcTemplate.query(sql, new ProduitRowMapper());
    }

    @Override
    public void saveProduit(Produit produit) {
       String sql = "INSERT INTO TProduits (nom, details, prix, quantite, idType) VALUES (?, ?, ?, ?, ?)";
       jdbcTemplate.update(sql, produit.getName(), produit.getDetails(), produit.getPrix(), produit.getQuantite(), produit.getIdType());
    }

    @Override
    public int deleteProduit(Produit produit) {
        String sql = "DELETE FROM TProduits WHERE id = ? ";
        return jdbcTemplate.update(sql, produit.getId());

    }

    public int updateProduit(Produit produit) {
        String sql = "UPDATE TProduits SET nom = ?, prix = ?, quantite = ?, idType = ? WHERE id = ?";
        return jdbcTemplate.update(sql, produit.getName(), produit.getPrix(), produit.getQuantite(), produit.getIdType(), produit.getId());
    }


    private static class ProduitRowMapper implements RowMapper<Produit> {
        @Override
        public Produit mapRow(ResultSet rs, int rowNum) throws SQLException {
            Produit produit = new Produit();
            produit.setId(rs.getInt("id"));
            produit.setName(rs.getString("nom"));
            produit.setDetails(rs.getString("details"));
            produit.setPrix(rs.getFloat("prix"));
            produit.setQuantite((rs.getInt("quantite")));
            produit.setIdType(rs.getInt("idType"));
            produit.setType(new Type(rs.getInt("idType"), rs.getString("type_name")));
            return produit;
        }
    }
}
