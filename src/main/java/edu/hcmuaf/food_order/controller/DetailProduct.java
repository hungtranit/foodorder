package edu.hcmuaf.food_order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DetailProduct {

    @Autowired
    SendDataAPI sendDataAPI;

    @GetMapping("/product-forum")
    public String redirectProductForum(Model model) {
        model.addAttribute("infoUser", sendDataAPI.getInfoUserSession());
        sendDataAPI.sendInfoUser();
        return "product-forum";
    }

}
