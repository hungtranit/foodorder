package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.repository.UserRepository;
import edu.hcmuaf.food_order.service.UserService;
import edu.hcmuaf.food_order.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegisterAPI {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String redirectRegister(Model model) {
        model.addAttribute("user", new InfoUser());
        System.out.println("get page register");
        return "register";
    }

    @PostMapping("/register-user")
    public String registerUser(Model model, @Valid @RequestBody InfoUser infoUser) {
        System.out.println("run method");
        System.out.println("user is: " + infoUser.toString());
        infoUser.setPassworduser(UserUtil.encryptPassword(infoUser.getPassworduser()));
        System.out.println("encrypt password " + infoUser.getPassworduser());
        System.out.println("after encrypt password: " + infoUser.toString());
//        userService.insertInfoUser(user);
        userRepository.save(infoUser);
        System.out.println("insert user into database");
        return "login";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("infoUser", new InfoUser());
        return "login";
    }

}
