package edu.hcmuaf.food_order.dao;

import edu.hcmuaf.food_order.model.Comment;
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
public class CommentDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Comment insertComment(Comment comment) {
        String SQL = "INSERT INTO CMTTOQUESTION(questionid, content, username)\r\n" + "VALUES(:questionid, :content, :username)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("questionid", comment.getQuestionID())
                .addValue("content", comment.getContent())
                .addValue("username", comment.getUserCMT());
        namedParameterJdbcTemplate.update(SQL, parameterSource, holder);
        return comment;
    }

}
