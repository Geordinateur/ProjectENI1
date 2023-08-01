package fr.eni.projecteni1.repository;

import fr.eni.projecteni1.bo.Type;
import fr.eni.projecteni1.repository.TypeRepository;
import fr.eni.projecteni1.service.ProduitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TypeRepositoryImpl implements TypeRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TypeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Type> getTypes() {
        String sql = "SELECT * FROM TTypes";
        return jdbcTemplate.query(sql, new TypeRowMapper());
    }

    private static class TypeRowMapper implements RowMapper<Type> {
       @Override
       public Type mapRow(ResultSet rs, int rowNum) throws SQLException {
           Type type = new Type();
           type.setLibelle(rs.getString("libelle"));
           return type;
       }
    }

}
