package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.Product;
import edu.hcmuaf.food_order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    SendDataAPI sendDataAPI;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    HttpSession httpSessionCart;

    List<Product> cart = new ArrayList<>();

    @GetMapping("/shopping-cart")
    public String getShopCart(Model model) {
        sendDataAPI.sendInfoUser();
        sendDataAPI.getSession().getAttribute("infoUser");
        sendInfoUser();
        sendDataAPI.getPage(model, "shopping-cart");
        return "shopping-cart";
    }

    private ModelAndView sendInfoUser() {
        ModelAndView mav = new ModelAndView("shopping-cart");
        mav.addObject("infoUser", sendDataAPI.getInfoUserSession());
        return mav;
    }

    private ModelAndView sendCart() {
        ModelAndView mav = new ModelAndView("header");
        mav.addObject("qualityProduct", cart.size());
        return mav;
    }

    @GetMapping("/shopping-cart/add/{productID}")
    public String addToShoppingCart(Model model, @PathVariable int productID) {
        sendDataAPI.sendInfoUser();
        sendDataAPI.getSession().getAttribute("infoUser");
        sendInfoUser();
        sendDataAPI.getPage(model, "shopping-cart");
        Product product = productRepository.getOne(productID);
        cart.add(product);
        httpSessionCart.setAttribute("cart", cart);
        model.addAttribute("qualityProduct", cart.size());
        sendCart();
        return "shopping-cart";
    }

}
