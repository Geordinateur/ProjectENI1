package fr.eni.projecteni1.repository;

import fr.eni.projecteni1.bo.User.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserImpl implements UserDAO {
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  private final JdbcTemplate jdbcTemplate;


  public UserImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
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
          ps.setString(1, user.getUserName());
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
      user.setId(rs.getInt("id"));
      return user;
    }
  }

  @Override
  public UserDTO getUser(String userName) {
    String sql = "SELECT * FROM users WHERE userName = ? ";
    UserDTO user = jdbcTemplate.queryForObject(sql, new Object[]{userName}, new OrderRowMapper());
    return user;
  }


}
