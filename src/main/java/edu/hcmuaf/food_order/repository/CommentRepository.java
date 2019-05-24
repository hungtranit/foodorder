package edu.hcmuaf.food_order.repository;

import edu.hcmuaf.food_order.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByQuestionID(int questionID);

    @Query("SELECT coalesce(max(e.id), 0) FROM Comment e")
    int getMaxId();

}
