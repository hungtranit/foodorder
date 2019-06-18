package edu.hcmuaf.food_order.dao;

import edu.hcmuaf.food_order.model.Product;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ProductDAO {

    @PersistenceContext
    private EntityManager entityManager;

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

    public Product insertProductAdmin(Product product) {
        String SQL = "insert into INFOPRODUCT(productname, img, price)" + "VALUES(:productname,:img,:price)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("productname", product.getProductname())
                .addValue("img", product.getImg())
                .addValue("price", product.getPrice());
        namedParameterJdbcTemplate.update(SQL, parameterSource, holder);
        return product;
    }

    @Transactional
    public List<Product> searchProduct(String searchText, int pageNo, int resultsPerPage) {
        FullTextQuery jpaQuery = searchProductQuery(searchText);
        jpaQuery.setMaxResults(resultsPerPage);
        jpaQuery.setFirstResult((pageNo - 1) * resultsPerPage);

        List<Product> productList = jpaQuery.getResultList();

        return productList;
    }

    @Transactional
    public int searchProductTotalCount(String searchText) {
        FullTextQuery jpaQuery = searchProductQuery(searchText);
        int productCount = jpaQuery.getResultSize();
        return productCount;
    }

    private FullTextQuery searchProductQuery(String searchText) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Product.class).get();

        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().wildcard().
                onFields("productname", "addressproduct", "username").boostedTo(5f).matching(searchText + "").createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Product.class);
        return jpaQuery;
    }
}
