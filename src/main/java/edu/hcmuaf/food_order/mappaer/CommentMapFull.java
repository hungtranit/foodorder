package edu.hcmuaf.food_order.mappaer;

import edu.hcmuaf.food_order.model.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapFull implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setQuestionID(rs.getInt("questionid"));
        comment.setContent(rs.getString("content"));
        comment.setUserCMT(rs.getString("username"));
        comment.setCmtID(rs.getInt("cmtid"));
        comment.setTimecmt(rs.getDate("timecmt"));
        return comment;
    }

}
