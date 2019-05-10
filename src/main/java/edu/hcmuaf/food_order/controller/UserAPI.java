package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.model.Question;
import edu.hcmuaf.food_order.repository.QuestionRepository;
import edu.hcmuaf.food_order.repository.UserRepository;
import edu.hcmuaf.food_order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserAPI {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserService userService;

//    List<Question> listQuestion;

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("infoUser", new InfoUser());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("infoUser") InfoUser infoUser) {
        String url = "";
        if (userService.login(infoUser.getUsername(), infoUser.getPassword())) {
            System.out.println("login success");
            infoUser = userRepository.getOne(infoUser.getUsername());
            sendUsername(infoUser);
            url = "index";
        } else {
            System.out.println("login fail");
            sendErrorLogin("Tài khoản hoặc khẩu không đúng");
            url = "login";
        }
        return url;
    }

    @RequestMapping(value = "infoUser", method = RequestMethod.GET)
    private ModelAndView sendUsername(InfoUser infoUser) {
        ModelAndView mav = new ModelAndView("header");
        mav.addObject("infoUser", infoUser);
        return mav;
    }

    @RequestMapping(value = "errorLogin", method = RequestMethod.GET)
    private ModelAndView sendErrorLogin(String message) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("errorLogin", message);
        return mav;
    }

    @GetMapping({"/", "/index"})
    public String getPageHome(Model model) {
        model.addAttribute("infoUser", new InfoUser());
        sendQuestion();
        return "index";
    }

    @RequestMapping(value = {"question"}, method = RequestMethod.POST)
    private ModelAndView sendQuestion() {
        ModelAndView mav = new ModelAndView("question");
        mav.addObject("question", questionRepository.findAll());
        return mav;
    }

}
