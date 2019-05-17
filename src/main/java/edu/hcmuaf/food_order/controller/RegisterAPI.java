package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.repository.UserRepository;
import edu.hcmuaf.food_order.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String registerUser(@ModelAttribute("user") InfoUser infoUser) {
        infoUser.setPassword(UserUtil.encryptPassword(infoUser.getPassword()));
        userRepository.save(infoUser);
        return "login";
    }


}
