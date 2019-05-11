package edu.hcmuaf.food_order.dao;


import edu.hcmuaf.food_order.mappaer.QuestionRowMapper;
import edu.hcmuaf.food_order.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class QuestionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Question> findDistinctType() {
        String sql = "SELECT DISTINCT QUESTION.typeproduct FROM QUESTION";
        RowMapper<Question> rowMapper = new QuestionRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

}
