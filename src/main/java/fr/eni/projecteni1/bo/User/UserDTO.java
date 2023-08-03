package fr.eni.projecteni1.bo.User;

import fr.eni.projecteni1.bo.Commande.CommandeDTO;
import jakarta.annotation.Nullable;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDTO implements UserDetails {
  @Nullable
  private CommandeDTO commande;

  private List<GrantedAuthority> authorities;
  private String userName;
  private String password;
  private Integer id;

  private String status;

  public UserDTO() {
  }

  public UserDTO(String userName, String password, Integer id, String status) {
    this.userName = userName;
    this.password = password;
    this.id = id;
    this.status = status;
  }

  public UserDTO(CommandeDTO commande, String userName, String password, Integer id, String status) {
    this.commande = commande;
    this.userName = userName;
    this.password = password;
    this.id = id;
    this.status = status;
  }

  public CommandeDTO getCommande() {
    return commande;
  }

  public void setCommande(CommandeDTO commande) {
    this.commande = commande;
  }



  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<GrantedAuthority> authorities){
    this.authorities = authorities;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "UserDTO{" +
      "commande=" + commande +
      ", userName='" + userName + '\'' +
      ", password='" + password + '\'' +
      ", id=" + id +
      ", status='" + status + '\'' +
      '}';
  }

}
