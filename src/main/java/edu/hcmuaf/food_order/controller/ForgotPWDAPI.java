package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.repository.UserRepository;
import edu.hcmuaf.food_order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ForgotPWDAPI {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SendDataAPI sendDataAPI;

    @Autowired
    UserService userService;

    @GetMapping("/forgot-password")
    public String getForgotPwd() {
        return "forgot-password";
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public ResponseEntity<?> createPassword(@Valid @RequestBody InfoUser infoUser) {
        boolean result;
        System.out.println("username: " + infoUser.getUsername());
        System.out.println("exist by user: " + userRepository.existsByUsername(infoUser.getUsername()));
        System.out.println("email: " + infoUser.getEmail());
        System.out.println("exist by email: " + userRepository.existsByEmail(infoUser.getEmail()));
        if (!userRepository.existsByUsername(infoUser.getUsername()) || !userRepository.existsByEmail(infoUser.getEmail())) {
            result = false;
        } else {
            userService.sendMail(infoUser.getEmail(), infoUser.getUsername());
            result = true;
        }
        return ResponseEntity.ok(result);
    }

}
