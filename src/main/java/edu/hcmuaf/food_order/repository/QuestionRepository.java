package edu.hcmuaf.food_order.repository;

import edu.hcmuaf.food_order.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

//    @Query("select distinct u from Question")
//    List<Question> listTypeQuestion();

}
