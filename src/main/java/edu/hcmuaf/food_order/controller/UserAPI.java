package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.dao.QuestionDAO;
import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.model.Question;
import edu.hcmuaf.food_order.repository.QuestionRepository;
import edu.hcmuaf.food_order.repository.UserRepository;
import edu.hcmuaf.food_order.service.QuestionService;
import edu.hcmuaf.food_order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserAPI {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

//    List<Question> listQuestion;

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("infoUser", new InfoUser());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(Model model, @ModelAttribute("infoUser") InfoUser infoUser) {
        String url = "";
        if (userService.login(infoUser.getUsername(), infoUser.getPassword())) {
            System.out.println("login success");
            infoUser = userRepository.getOne(infoUser.getUsername());
            sendUsername(infoUser);
            url = "index";
        } else {
            System.out.println("login fail");
            String massage = "Tài khoản hoặc khẩu không đúng";
            model.addAttribute("errorLogin", massage);
            sendErrorLogin(massage);
            url = "login";
        }
        return url;
    }

    @RequestMapping(value = "infoUser", method = RequestMethod.POST)
    private ModelAndView sendUsername(InfoUser infoUser) {
        ModelAndView mav = new ModelAndView("header");
        mav.addObject("infoUser", infoUser);
        return mav;
    }

    @RequestMapping(value = "errorLogin", method = RequestMethod.POST)
    private ModelAndView sendErrorLogin(String message) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("errorLogin", message);
        return mav;
    }

    @GetMapping({"/", "/index"})
    public String getPageHome(Model model) {
        model.addAttribute("infoUser", new InfoUser());
        model.addAttribute("question", questionRepository.findAll());
        sendListQuestion(questionRepository.findAll());
        model.addAttribute("typequestion", questionService.findDistinctType());
        sendTypeQuestion(questionService.findDistinctType());
        return "index";
    }

    @RequestMapping(value = "question", method = RequestMethod.POST)
    private ModelAndView sendListQuestion(List<Question> questionList) {
        ModelAndView mav = new ModelAndView("question");
        mav.addObject("question", questionList);
        return mav;
    }

    @RequestMapping(value = "typequestion", method = RequestMethod.POST)
    private ModelAndView sendTypeQuestion(List<Question> typeQuestion) {
        ModelAndView mav = new ModelAndView("question");
        mav.addObject("typequestion", typeQuestion);
        return mav;
    }

}
