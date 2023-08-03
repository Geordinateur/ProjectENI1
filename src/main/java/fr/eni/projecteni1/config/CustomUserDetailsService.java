package fr.eni.projecteni1.config;

import fr.eni.projecteni1.bo.User.UserDTO;
import fr.eni.projecteni1.repository.UserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserDAO userDAO;

  public CustomUserDetailsService(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDTO userDTO = userDAO.getUser(username);


    if (userDTO == null) {
      throw new UsernameNotFoundException("User not found: " + username);
    }


    return userDTO;
  }
}


