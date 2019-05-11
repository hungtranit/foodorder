package edu.hcmuaf.food_order.service;

import edu.hcmuaf.food_order.dao.QuestionDAO;
import edu.hcmuaf.food_order.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public List<Question> findDistinctType() {
        return questionDAO.findDistinctType();
    }


}
