package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.service.ProductAdminService;
import edu.hcmuaf.food_order.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ProductAdminService productService;

    @Autowired
    SendDataAPI sendDataAPI;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductAdminService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;

    }

    @RequestMapping(value = "/products")
    public ModelAndView products() {
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("products", productService.findAllProducts());
        return modelAndView;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        return modelAndView;
    }

    @GetMapping("/shoppingCart/addProduct/{productId}")
    public ModelAndView addProductToCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

    @GetMapping("/shopping-cart")
    public String getShopCart(Model model) {
        sendDataAPI.sendInfoUser();
        sendDataAPI.getSession().getAttribute("infoUser");
        sendInfoUser();
        sendDataAPI.getPage(model, "shopping-cart");
        return "shopping-cart";
    }

    public ModelAndView sendInfoUser() {
        ModelAndView mav = new ModelAndView("shopping-cart");
        mav.addObject("infoUser", sendDataAPI.getInfoUserSession());
        return mav;
    }

}
