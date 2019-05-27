package edu.hcmuaf.food_order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DetailProduct {

    @Autowired
    SendDataAPI sendDataAPI;

    @GetMapping("/product-forum/{product-id}")
    public String redirectProductForum(Model model) {
        sendDataAPI.sendInfoUser();
        return "product-forum";
    }

    @GetMapping("/post-product")
    public String getPostProduct() {
//        if (sendDataAPI.getInfoUserSession().getUsername() == null) {
//            return "login";
//        }
        return "post-product";
    }

}
