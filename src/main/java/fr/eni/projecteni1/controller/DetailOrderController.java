package fr.eni.projecteni1.controller;

import fr.eni.projecteni1.bo.Commande.Commande;
import fr.eni.projecteni1.bo.DetailCommande.DetailOrderDTO;
import fr.eni.projecteni1.bo.Produit.Produit;
import fr.eni.projecteni1.service.DetailOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@RestController
public class DetailOrderController {

  @Autowired
  private DetailOrderService detailOrder;
  @PostMapping("/saveDetailOrder")
  @CrossOrigin
  public ResponseEntity<Map<String,String>> saveOrder(@RequestBody DetailOrderDTO detailOrderDTO) throws SQLException {
    System.out.println("je suis dans le controller");
    System.out.println(detailOrderDTO.getQuantite() + " quantite dans le controller ");
    int rowAdded = this.detailOrder.createDetailOrder(detailOrderDTO.getCommande(), detailOrderDTO.getProduit(), detailOrderDTO.getQuantite());
    System.out.println(detailOrderDTO.getQuantite());
    if(rowAdded == 0 ){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("Detail order " , "Failed/Error"));
    }else{
      return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Detail Order", " Added in Order"));
    }
  }

  @DeleteMapping("/deleteDetailOrder/{id}")
  public ResponseEntity<Map<String, String>> deleteDetailorder(@PathVariable Integer id) throws SQLException {
    Boolean isSuccess = this.detailOrder.deleteDetailOrder(id);
    if (isSuccess) {
      return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Detail Order " + id, " removed"));
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("Detail Order : " + id, " Failed/Error"));
    }
  }
}
