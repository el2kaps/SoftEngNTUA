package gr.ntua.ece.softeng2021.backend.data;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class IntMapper implements RowMapper<Integer> {

    @Override
    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {

      Integer retme = rs.getInt("mycnt");
      return retme;

    }
}
