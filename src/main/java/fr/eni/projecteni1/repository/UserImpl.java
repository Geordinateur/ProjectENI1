package fr.eni.projecteni1.repository;

import fr.eni.projecteni1.bo.User.UserDTO;
import fr.eni.projecteni1.controller.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserImpl implements UserDAO {

  Logger logger = LoggerFactory.getLogger(LoggingController.class);

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  private final JdbcTemplate jdbcTemplate;


  public UserImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Boolean authUser(String userName, String password) {
    String sql = "SELECT password FROM users WHERE userName = ?";
    String encodedPassword = jdbcTemplate.queryForObject(sql, new Object[]{userName}, String.class);
    if(encodedPassword == null ){
      throw new UsernameNotFoundException("Error");
    }
    return passwordEncoder.matches(password, encodedPassword);
  }

  @Override
  public void createUser(UserDTO user) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO users(userName,password) VALUES(?,?)";
    try {
      jdbcTemplate.update(new PreparedStatementCreator() {
        @Override
        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
          user.setPassword(passwordEncoder.encode(user.getPassword()));
          PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
          ps.setString(1, user.getUsername());
          ps.setString(2,user.getPassword());
          System.out.println("password " + user.getPassword());
          return ps;
        }
      }, keyHolder);
      if (keyHolder.getKey() == null) {
        throw new SQLException("Failed to insert User, no ID obtained.");
      }
      user.setId(keyHolder.getKey().intValue());
    } catch (DataAccessException | SQLException error) {
      logger.error(error.toString());
      error.printStackTrace();
      throw new RuntimeException(error);
    }
  }

  @Override
  public int deleteUser(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("user id can not be null");
    }
    String sql = "DELETE FROM users WHERE id = ? ";

    try {
      int rowAffected = jdbcTemplate.update(sql, id);
      if (rowAffected == 0) {
        System.out.println("Aucun utilisateur supprimé");
      } else {
        System.out.println(rowAffected + " Utilisateur supprimé");
      }
      return rowAffected;
    } catch (Exception error) {
      logger.error(error.toString());
      error.printStackTrace();
      throw error;
    }
  }

  public static class OrderRowMapper implements RowMapper<UserDTO> {
    @Override
    public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
      UserDTO user = new UserDTO();
      user.setUserName(rs.getString("userName"));
      user.setStatus(rs.getString("status"));
      user.setPassword(rs.getString("password"));
      List<GrantedAuthority> authorities = new ArrayList<>();
      switch (user.getStatus()){
        case "CLIENT":
        authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
        break;
        case "EMPLOYE":
          authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYE"));
          break;
        case "CUISINIER":
          authorities.add(new SimpleGrantedAuthority("ROLE_CUISINIER"));
          break;
        case "GERANT":
          authorities.add(new SimpleGrantedAuthority("ROLE_GERANT"));
          break;
      }
      user.setAuthorities(authorities);
      user.setId(rs.getInt("id"));
      return user;
    }
  }

  @Override
  public UserDTO getUser(String userName) {
    String sql = "SELECT userName,status,password, id FROM users WHERE userName = ? ";
    UserDTO user = jdbcTemplate.queryForObject(sql, new Object[]{userName}, new OrderRowMapper());

    return user;
  }


}
