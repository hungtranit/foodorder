package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.repository.UserRepository;
import edu.hcmuaf.food_order.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterAPI {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String redirectRegister(Model model) {
        model.addAttribute("user", new InfoUser());
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public String registerUser(@ModelAttribute("user") InfoUser user) {
        System.out.println("run method");
        user.setPassword(UserUtil.encryptPassword(user.getPassword()));
        System.out.println("encrypt password");
        //
        System.out.println("save user");
        return "login";
    }


}
