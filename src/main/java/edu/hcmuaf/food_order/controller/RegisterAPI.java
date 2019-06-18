package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.repository.UserRepository;
import edu.hcmuaf.food_order.service.UserService;
import edu.hcmuaf.food_order.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterAPI {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String redirectRegister(Model model) {
        model.addAttribute("infoUser", new InfoUser());
        System.out.println("get page register");
        return "register";
    }

    @RequestMapping(value = "/register-user", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String registerUser(Model model, InfoUser infoUser) {
        System.out.println("run method");
        System.out.println("user is: " + infoUser.toString());
        infoUser.setPassworduser(UserUtil.encryptPassword(infoUser.getPassworduser()));
        System.out.println("encrypt password " + infoUser.getPassworduser());
        System.out.println("after encrypt password: " + infoUser.toString());
        userRepository.save(infoUser);
        System.out.println("insert user into database");
        String msg = "Đăng kí tài khoản thành công!!!";
        model.addAttribute("registerSuccess", msg);
        sendMsgSuccess(msg);
        return "login";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("infoUser", new InfoUser());
        return "login";
    }

    private ModelAndView sendMsgSuccess(String msg) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("registerSuccess", msg);
        return mav;
    }

}
