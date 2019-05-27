package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.Comment;
import edu.hcmuaf.food_order.model.Question;
import edu.hcmuaf.food_order.model.Rep;
import edu.hcmuaf.food_order.repository.CommentRepository;
import edu.hcmuaf.food_order.repository.QuestionRepository;
import edu.hcmuaf.food_order.repository.RepRepository;
import edu.hcmuaf.food_order.service.CommentService;
import edu.hcmuaf.food_order.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @Autowired
    SendDataAPI sendDataAPI = new SendDataAPI();

    @Autowired
    CommentService commentService;

    private Question question = null;
    private int idQuestion = 0;
    private String url = "detail-question";

    @GetMapping("/detail-question/{questionID}")
    public String getQuestion(Model model, @PathVariable int questionID) {
        question = questionRepository.getOne(questionID);
        idQuestion = questionID;
        sendDetailQuestion(model, questionID, url);
        return url;
    }

    @RequestMapping(value = "typequestion", method = RequestMethod.GET)
    private ModelAndView sendTypeQuestion() {
        ModelAndView mav = new ModelAndView("detail-question");
        mav.addObject("typequestion", questionService.findDistinctType());
        return mav;
    }

    @RequestMapping(value = "/add-comment", method = RequestMethod.POST)
    public ResponseEntity<?> addComment(@Valid @RequestBody Comment comment) {
        comment = new Comment(idQuestion, comment.getContent(), sendDataAPI.getInfoUserSession().getUsername());
        commentService.insertComment(comment);
        comment = commentRepository.getOne(commentRepository.getMaxId());
        return ResponseEntity.ok(comment);
    }

    @RequestMapping(value = "/rep", method = RequestMethod.POST)
    public ResponseEntity<?> addRep(@Valid @RequestBody Rep rep) {
        rep = new Rep(rep.getContent(), rep.getCmtid(), sendDataAPI.getInfoUserSession().getUsername());
        repRepository.save(rep);
        rep = repRepository.getOne(repRepository.getMaxId());
        return ResponseEntity.ok(rep);
    }

    private void sendDetailQuestion(Model model, int questionID, String url) {
        model.addAttribute("detailquestion", question);
        model.addAttribute("listComment", commentRepository.findAllByQuestionID(questionID));
        model.addAttribute("listRep", repRepository.findAll());
        sendDataAPI.sendInfoUser();
        sendDataAPI.getSession().getAttribute("infoUser");
        sendDataAPI.getPage(model, url);
        model.addAttribute("typequestion", questionService.findDistinctType());
        sendTypeQuestion();
        model.addAttribute("checkUser", sendDataAPI.getInfoUserSession());
        model.addAttribute("comment", new Comment());
    }

}
