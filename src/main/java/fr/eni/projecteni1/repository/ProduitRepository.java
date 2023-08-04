package fr.eni.projecteni1.repository;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projecteni1.bo.Produit;
import org.springframework.dao.DataAccessException;

public interface ProduitRepository {
    public List<Produit> getProduits() throws DataAccessException;
    public void saveProduit(Produit produit);
    public int deleteProduit(Integer id);
    public int updateProduit(Integer idProduit, Produit produit);

}
