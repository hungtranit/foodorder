package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.repository.UserRepository;
import edu.hcmuaf.food_order.service.UserService;
import edu.hcmuaf.food_order.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String getForgotPwd(Model model) {
        model.addAttribute("infoUser", new InfoUser());
        return "forgot-password";
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public String createPassword(Model model, @ModelAttribute("infoUser") InfoUser infoUser) {
        String result;
        System.out.println("username: " + infoUser.getUsername());
        System.out.println("exist by user: " + userRepository.existsByUsername(infoUser.getUsername()));
        System.out.println("email: " + infoUser.getEmail());
        System.out.println("exist by email: " + userRepository.existsByEmail(infoUser.getEmail()));
        if (!userRepository.existsByUsername(infoUser.getUsername()) || !userRepository.existsByEmail(infoUser.getEmail())) {
            result = "forgot-password";
            String msg = "Tài khoản hoặc email không đúng!";
            model.addAttribute("errorForgetPassword", msg);
            sendErrorForgetPassword(msg);
        } else {
            userService.sendMailCreatePassword(infoUser.getEmail(), infoUser.getUsername());
            String msg = "Mật khẩu mới đã gửi đến email!";
            model.addAttribute("msgSuccess", msg);
            sendMsgSuccess(msg);
            result = "login";
        }
        return result;
    }

    public ModelAndView sendErrorForgetPassword(String msg) {
        ModelAndView mav = new ModelAndView("forgot-password");
        mav.addObject("errorForgetPassword", msg);
        return mav;
    }

    public ModelAndView sendMsgSuccess(String msg) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("msgSuccess", msg);
        return mav;
    }

}
