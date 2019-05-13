package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.Comment;
import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.model.Question;
import edu.hcmuaf.food_order.model.Rep;
import edu.hcmuaf.food_order.repository.CommentRepository;
import edu.hcmuaf.food_order.repository.QuestionRepository;
import edu.hcmuaf.food_order.repository.RepRepository;
import edu.hcmuaf.food_order.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ForumAPI {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    RepRepository repRepository;

    @Autowired
    QuestionService questionService;

    @GetMapping("/detail-question/{questionID}")
    public String getQuestion(Model model, @PathVariable int questionID, Question question) {
        question = questionRepository.getOne(questionID);
        model.addAttribute("detailquestion", question);
        sendDetailQuestion(question);
        model.addAttribute("listComment", commentRepository.findAllByQuestionID(questionID));
        sendListComment(commentRepository.findAllByQuestionID(questionID));
        model.addAttribute("listRep", repRepository.findAll());
        sendListRep(repRepository.findAll());
        model.addAttribute("infoUser", new InfoUser());
        sendUsername(new InfoUser());
        model.addAttribute("typequestion", questionService.findDistinctType());
        sendTypeQuestion(questionService.findDistinctType());
        return "detail-question";
    }

    @RequestMapping(value = "detailquestion", method = RequestMethod.GET)
    private ModelAndView sendDetailQuestion(Question question) {
        ModelAndView mav = new ModelAndView("detail-question");
        mav.addObject("detailquestion", question);
        return mav;
    }

    @RequestMapping(value = "listComment", method = RequestMethod.GET)
    private ModelAndView sendListComment(List<Comment> commentList) {
        ModelAndView mav = new ModelAndView("detail-question");
        mav.addObject("listComment", commentList);
        return mav;
    }

    @RequestMapping(value = "listRep", method = RequestMethod.GET)
    private ModelAndView sendListRep(List<Rep> repList) {
        ModelAndView mav = new ModelAndView("detail-question");
        mav.addObject("listRep", repList);
        return mav;
    }

    @RequestMapping(value = "infoUser", method = RequestMethod.GET)
    private ModelAndView sendUsername(InfoUser infoUser) {
        ModelAndView mav = new ModelAndView("header");
        mav.addObject("infoUser", infoUser);
        return mav;
    }

    @RequestMapping(value = "typequestion", method = RequestMethod.GET)
    private ModelAndView sendTypeQuestion(List<Question> typeQuestion) {
        ModelAndView mav = new ModelAndView("detail-question");
        mav.addObject("typequestion", typeQuestion);
        return mav;
    }

}
