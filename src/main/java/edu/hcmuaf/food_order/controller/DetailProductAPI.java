package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.model.Product;
import edu.hcmuaf.food_order.repository.ProductRepository;
import edu.hcmuaf.food_order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class DetailProductAPI {

    @Autowired
    SendDataAPI sendDataAPI;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @GetMapping("/product-forum/{product-id}")
    public String redirectProductForum(Model model) {
        sendDataAPI.sendInfoUser();
        return "product-forum";
    }

    @GetMapping("/post-product")
    public String getPostProduct(Model model) {
        if (sendDataAPI.getInfoUserSession().getUsername() == null) {
            model.addAttribute("infoUser", new InfoUser());
            return "login";
        }
        return "post-product";
    }

    @PostMapping(value = "/post-product")
    public ResponseEntity<?> postProduct(@Valid @RequestBody Product product) {
        product = new Product(product.getTypeproduct(), product.getProductname(), product.getDecriptionproduct(),
                product.getAddressproduct(), product.getImg(), product.getPrice(),
                sendDataAPI.getInfoUserSession().getUsername(), product.getPhone(), product.getQuantity());
        System.out.println("frint first: " + product.toString());
        productService.insertProduct(product);
        return ResponseEntity.ok("post-product");
    }

    @GetMapping("/detail-product/{productID}")
    public String getDetailProduct(Model model, @PathVariable int productID) {
        model.addAttribute("infoUser", sendDataAPI.getInfoUserSession());
        sendDataAPI.sendInfoUser();
        model.addAttribute("detailProduct", productRepository.getOne(productID));
        getProduct(productID);
        model.addAttribute("qualityProduct", sendDataAPI.getCart().size());
        sendDataAPI.sendCart();
        return "detail-product";
    }

    private ModelAndView getProduct(int productID) {
        ModelAndView mav = new ModelAndView("detail-product");
        mav.addObject("detailProduct", productRepository.getOne(productID));
        return mav;
    }

}
