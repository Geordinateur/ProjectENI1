package fr.eni.projecteni1.service;

import fr.eni.projecteni1.bo.User.UserDTO;
import fr.eni.projecteni1.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {

  @Autowired
  private UserDAO user;

  public void createUser(UserDTO user){
    this.user.createUser(user);
  }

  public UserDTO getUser(String userName) throws SQLException {
    UserDTO user = this.user.getUser(userName);
    if(user == null){
      throw new RuntimeException();
    }
    return user;

  }

  public int deleteUser(int userId) throws SQLException {
    int rowAffected = this.user.deleteUser(userId);
    if(rowAffected == 0){
      throw new SQLException();
    }
    return rowAffected;
  }

  public Boolean authUser(String userName, String password){
    Boolean isSuccess = this.user.authUser(userName,password);
    if(!isSuccess){
      System.out.println("Erreur de mot de passe");
    }
    return isSuccess;
  }


}
