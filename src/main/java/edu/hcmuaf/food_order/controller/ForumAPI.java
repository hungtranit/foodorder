package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.model.Question;
import edu.hcmuaf.food_order.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForumAPI {

//    @Autowired
//    QuestionRepository questionRepository;
//
//    @GetMapping("/detail-question/{questionID}")
//    public String getQuestion(Model model, int questionID, Question question) {
//        model.addAttribute("detail-question", questionRepository.getOne(questionID));
//        sendDetailQuestion(question);
//        return "detail-question";
//    }
//
//    @RequestMapping(value = "detail-question", method = RequestMethod.GET)
//    private ModelAndView sendDetailQuestion(Question question) {
//        ModelAndView mav = new ModelAndView("detail-question");
//        mav.addObject("detail-question", question);
//        return mav;
//    }

    @GetMapping("detail-question")
    public String getDetail(Model model) {
        model.addAttribute("infoUser", new InfoUser());
        return "detail-question";
    }

}
