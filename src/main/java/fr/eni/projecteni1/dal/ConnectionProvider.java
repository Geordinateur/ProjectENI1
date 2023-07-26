package fr.eni.projecteni1.dal;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionProvider {
  private static DataSource dataSource;

  static{
    Context context;
    try{
      context = new InitialContext();
      ConnectionProvider.dataSource=(DataSource)context.lookup("java:comp/env/jdbc/projetEni");
    }catch(NamingException e){
      e.printStackTrace();
      throw new RuntimeException("Impossible d'accéder à la base de données");
    }
  }

  public static Connection getConnection() throws SQLException {
    return ConnectionProvider.dataSource.getConnection();
  }

}
