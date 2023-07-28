package fr.eni.projecteni1.controller;

import fr.eni.projecteni1.bo.Commande.Commande;
import fr.eni.projecteni1.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@RestController
public class CommandeController {



  @Autowired
  private CommandeService commandeService;

  @PostMapping("/saveCommande")
  @CrossOrigin
  public ResponseEntity<Map<String, String>> saveOrder(@RequestBody Commande commande) throws SQLException {
    Boolean isSuccess = this.commandeService.saveOrder(commande);
    if(isSuccess){
      return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Save Order", "Success"));
    } else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Save Order","Failed/Error"));
    }
  }

  @DeleteMapping("/deleteOrder/{id}")
  @CrossOrigin
  public ResponseEntity<Map<String, String>> deleteOrder(@PathVariable Integer id) throws SQLException {
    Boolean isSuccess = this.commandeService.deleteOrder(id);
    if(isSuccess){
      return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Delete Order", "success"));
    }else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Delete Order ", "Failed/Error"));
    }
  }
}
