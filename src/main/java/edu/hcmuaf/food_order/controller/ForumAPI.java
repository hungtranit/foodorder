package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.model.Question;
import edu.hcmuaf.food_order.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForumAPI {

    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/detail-question/{questionID}")
    public String getQuestion(Model model, @PathVariable int questionID, Question question) {
        question = questionRepository.getOne(questionID);
        model.addAttribute("detailquestion", question);
        sendDetailQuestion(question);
        model.addAttribute("infoUser", new InfoUser());
        sendUsername(new InfoUser());
        return "detail-question";
    }

    @RequestMapping(value = "detailquestion", method = RequestMethod.GET)
    private ModelAndView sendDetailQuestion(Question question) {
        ModelAndView mav = new ModelAndView("detail-question");
        mav.addObject("detailquestion", question);
        return mav;
    }

    @RequestMapping(value = "infoUser", method = RequestMethod.GET)
    private ModelAndView sendUsername(InfoUser infoUser) {
        ModelAndView mav = new ModelAndView("header");
        mav.addObject("infoUser", infoUser);
        return mav;
    }

}
