package fr.eni.projecteni1.controller;

import fr.eni.projecteni1.bo.User.UserDTO;
import fr.eni.projecteni1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

  @Autowired
  private UserService user;

  @PostMapping("/login")
  @CrossOrigin
  public Boolean login(@RequestBody UserDTO userDTO){
    Boolean isSuccess = this.user.authUser(userDTO.getUsername(),userDTO.getPassword());
    if(!isSuccess){
      System.out.println("Erreur");
    }
    return isSuccess;
  }


  @PostMapping("/saveUser")
  @CrossOrigin
  public ResponseEntity<Map<String,String>> createUser(@RequestBody UserDTO user){
    Boolean isSuccess = true;
    try{
      this.user.createUser(user);
    }catch(Exception error){
      isSuccess = false;
    }
    if(isSuccess){
      return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("User " , " Created"));
    }else{
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("User " + user.getId().toString(), " Failed/Error"));
    }

  }

  @GetMapping("/getUser/{userName}")
  @CrossOrigin
  public ResponseEntity<UserDTO> getUser(@PathVariable String userName) throws SQLException {
   UserDTO user = this.user.getUser(userName);
   return ResponseEntity.ok(user);
  }





  @DeleteMapping ("/deleteUser/{id}")
  @CrossOrigin
  public void deteleUser(@PathVariable int id) {
    try {
      this.user.deleteUser(id);
    } catch (SQLException error) {
      throw new RuntimeException();
    }
  }


}
