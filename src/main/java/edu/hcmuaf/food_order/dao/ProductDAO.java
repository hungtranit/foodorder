package edu.hcmuaf.food_order.dao;

import edu.hcmuaf.food_order.model.Comment;
import edu.hcmuaf.food_order.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Product insertProduct(Product product) {
        String SQL = "insert into INFOPRODUCT(typeproduct,productname,decriptionproduct,addressproduct,img,price," +
                "username,phone)\r\n" + "VALUES(:typeproduct,:productname,:decriptionproduct,:addressproduct,:img,:price,:username,:phone)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("typeproduct", product.getTypeproduct())
                .addValue("productname", product.getProductname())
                .addValue("decriptionproduct", product.getDecriptionproduct())
                .addValue("addressproduct", product.getAddressproduct())
                .addValue("img", product.getImg())
                .addValue("price", product.getPrice())
                .addValue("username", product.getUsername())
                .addValue("phone", product.getPhone());
        namedParameterJdbcTemplate.update(SQL, parameterSource, holder);
        return product;
    }

}
