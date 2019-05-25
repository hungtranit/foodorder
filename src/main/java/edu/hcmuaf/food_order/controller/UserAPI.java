package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.repository.QuestionRepository;
import edu.hcmuaf.food_order.repository.UserRepository;
import edu.hcmuaf.food_order.service.QuestionService;
import edu.hcmuaf.food_order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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

    @Autowired
    SendDataAPI sendDataAPI;

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("infoUser", new InfoUser());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(Model model, @ModelAttribute("infoUser") InfoUser infoUser) {
        String url;
        if (userService.login(infoUser.getUsername(), infoUser.getPassword())) {
            System.out.println("login success");
            infoUser = userRepository.getOne(infoUser.getUsername());
            sendDataAPI.setInfoUserSession(infoUser);
            sendDataAPI.getSession().setAttribute("infoUser", infoUser);
            getPageHome(model);
            url = "index";
        } else {
            System.out.println("login fail");
            String massage = "Tài khoản hoặc khẩu không đúng";
            model.addAttribute("errorLogin", massage);
            url = "login";
        }
        return url;
    }

    @GetMapping({"/", "/index"})
    public String getPageHome(Model model) {
        model.addAttribute("infoUser", sendDataAPI.getInfoUserSession());
        sendDataAPI.sendInfoUser();
        model.addAttribute("question", questionRepository.findAll());
        sendListQuestion();
        model.addAttribute("typequestion", questionService.findDistinctType());
        sendTypeQuestion();
        return "index";
    }

    @RequestMapping(value = "typequestion", method = RequestMethod.POST)
    public ModelAndView sendTypeQuestion() {
        ModelAndView mav = new ModelAndView("question");
        mav.addObject("typequestion", questionService.findDistinctType());
        return mav;
    }

    @RequestMapping(value = "question", method = RequestMethod.POST)
    public ModelAndView sendListQuestion() {
        ModelAndView mav = new ModelAndView("question");
        mav.addObject("question", questionRepository.findAll());
        return mav;
    }

    @RequestMapping(value = "errorLogin", method = RequestMethod.POST)
    public ModelAndView sendError(String errorLogin) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("errorLogin", errorLogin);
        return mav;
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("infoUser");
        sendDataAPI.setInfoUserSession(new InfoUser());
        model.addAttribute("infoUser", sendDataAPI.getInfoUserSession());
        sendDataAPI.sendInfoUser();
        model.addAttribute("question", questionRepository.findAll());
        model.addAttribute("typequestion", questionService.findDistinctType());
        return "index";
    }

    @RequestMapping(value = "/check-email", method = RequestMethod.POST)
    public int checkEmail(@RequestParam(value = "email") String email) {
        int message;
        System.out.println("check email: ............");
        if (userRepository.existsByEmail(email)) {
            message = 1;
        } else {
            message = 0;
        }
        return message;
    }



}
