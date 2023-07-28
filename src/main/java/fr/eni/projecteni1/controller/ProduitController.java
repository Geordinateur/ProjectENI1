package fr.eni.projecteni1.controller;

import fr.eni.projecteni1.bo.Produit;
import fr.eni.projecteni1.repository.ProduitRepository;
import fr.eni.projecteni1.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @GetMapping({"/Produits"})
    @CrossOrigin
    public List<Produit> getProduit() {
        return this.produitService.getProduits();
    }

    @PostMapping({"/SaveProduit"})
    @CrossOrigin
    public String ajouterProduit(Produit produit) {
        this.produitService.saveProduit(produit);
        return "redirect:/Produits";
    }

    @PostMapping({"/UpdateProduit"})
    @CrossOrigin
    public ResponseEntity<Map<String, String>> updateProduit(@RequestBody Produit produit) throws SQLException {
        int isSuccess = this.produitService.updateProduit(produit);
        if(isSuccess > 0){
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Product updated", "Success"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Product updated", "Failed/Error"));
        }
    }

    @DeleteMapping("/DeleteProduit")
    @CrossOrigin
    public ResponseEntity<Map<String, String>> supprimerProduit(@RequestBody Produit produit) throws SQLException {
        int isSuccess = this.produitService.deleteProduit(produit);
        if(isSuccess > 0){
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Delete Order", "success"));
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Delete Order ", "Failed/Error"));
        }
    }

}
