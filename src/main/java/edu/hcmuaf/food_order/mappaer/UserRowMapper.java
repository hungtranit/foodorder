package edu.hcmuaf.food_order.mappaer;

import edu.hcmuaf.food_order.model.InfoUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<InfoUser> {

    @Override
    public InfoUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        InfoUser infoUser = new InfoUser();
        infoUser.setUsername(rs.getString("username"));
        infoUser.setPassword(rs.getString("passworduser"));
        infoUser.setFirstName(rs.getString("firstname"));
        infoUser.setLastName(rs.getString("lastname"));
        infoUser.setAddress(rs.getString("addressofuser"));
        infoUser.setMail(rs.getString("email"));
        infoUser.setPhone(rs.getString("phone"));
        infoUser.setAvatar(rs.getString("avatar"));
        return null;
    }
}
