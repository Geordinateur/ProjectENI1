package fr.eni.projecteni1.service;

import fr.eni.projecteni1.bo.User.UserDTO;
import fr.eni.projecteni1.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
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
    try{
      UserDTO user = this.user.getUser(userName);
      if(user == null){
        throw new RuntimeException();
      }
      return user;
    }catch (SQLException error) {
      System.out.println("Erreur SQL : " + error.getMessage());
      System.out.println("Code d'état SQL : " + error.getSQLState());
      System.out.println("Code d'erreur du fournisseur : " + error.getErrorCode());
      error.printStackTrace();
      throw new RuntimeException("SQL erred", error);
    }

  }

  public int deleteUser(int userId) throws SQLException {
    int rowAffected = this.user.deleteUser(userId);
    if(rowAffected == 0){
      throw new SQLException();
    }
    return rowAffected;
  }


}
