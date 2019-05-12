package edu.hcmuaf.food_order.repository;

import edu.hcmuaf.food_order.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByQuestionID(int questionID);

}
