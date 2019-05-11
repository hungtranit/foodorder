package edu.hcmuaf.food_order.mappaer;

import edu.hcmuaf.food_order.model.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setTypeproduct(rs.getString("typeproduct"));
        return question;
    }
}
