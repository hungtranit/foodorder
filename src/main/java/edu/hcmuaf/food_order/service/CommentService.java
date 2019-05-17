package edu.hcmuaf.food_order.service;

import edu.hcmuaf.food_order.dao.CommentDAO;
import edu.hcmuaf.food_order.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentDAO commentDAO;

    public void insertComment(Comment comment) {
        commentDAO.insertComment(comment);
    }

}
