package fr.eni.projecteni1.controller;

import fr.eni.projecteni1.bo.Commande.Commande;
import fr.eni.projecteni1.bo.Commande.CommandeDTO;
import fr.eni.projecteni1.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class CommandeController {


  @Autowired
  private CommandeService commandeService;

  @PostMapping("/saveOrder")
  @CrossOrigin
  public ResponseEntity<Map<String, String>> saveOrder(@RequestBody CommandeDTO commande){
    Boolean isSuccess = true;
    System.out.println(commande.toString());
    try{
      this.commandeService.saveOrder(commande);
    }catch(SQLException error){
      isSuccess = false;
    }
    if (isSuccess) {
      return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Commande: ", commande.getId().toString()));
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Save Order", "Failed/Error"));
    }
  }

  @DeleteMapping("/deleteOrder/{id}")
  @CrossOrigin
  public ResponseEntity<Map<String, String>> deleteOrder(@PathVariable Integer id) throws SQLException {
    Boolean isSuccess = this.commandeService.deleteOrder(id);
    if (isSuccess) {
      return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Delete Order", "success"));
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Delete Order ", "Failed/Error"));
    }
  }

  @GetMapping("/orderById/{id}")
  @CrossOrigin
  public ResponseEntity<Commande> getOrderById(@PathVariable Integer id) throws SQLException{
    Commande order =this.commandeService.getOrderById(id);
    return ResponseEntity.ok(order);
  }

  @GetMapping("/orderById")
  @CrossOrigin
  public List<Commande> getCommandes() throws SQLException {
    return this.commandeService.getCommandes();
  }

}
