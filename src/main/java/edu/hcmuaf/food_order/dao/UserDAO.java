package edu.hcmuaf.food_order.dao;

import edu.hcmuaf.food_order.model.InfoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class UserDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public InfoUser insertInfoUser(InfoUser infoUser) {
        String SQL = "INSERT INTO INFOUSER(username, passworduser, fullname, addressofuser, email, phone, avatar)\r\n"
                + "VALUES(:username, :passworduser, :fullname, :addressofuser, :email, :phone, :avatar)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("username", infoUser.getUsername())
                .addValue("passworduser", infoUser.getPassworduser())
                .addValue("fullname", infoUser.getFullname())
                .addValue("addressofuser", infoUser.getAddressofuser())
                .addValue("email", infoUser.getEmail())
                .addValue("phone", infoUser.getPhone())
                .addValue("avatar", infoUser.getAvatar());
        namedParameterJdbcTemplate.update(SQL, parameterSource, holder);
        return infoUser;
    }

}
