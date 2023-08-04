package fr.eni.projecteni1.repository;

import fr.eni.projecteni1.bo.User.UserDTO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

public interface UserDAO {

   Boolean authUser(String userName, String password);
   void createUser(UserDTO userDto);
   UserDTO getUser(String userName) ;
   int deleteUser(Integer id);
}
