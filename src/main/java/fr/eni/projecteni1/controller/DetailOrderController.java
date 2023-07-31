package fr.eni.projecteni1.controller;

import fr.eni.projecteni1.service.DetailOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@RestController
public class DetailOrderController {

  @Autowired
  private DetailOrderService detailOrder;

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
