package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.Product;
import edu.hcmuaf.food_order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductAPI {
    @Autowired
    ProductService productService;
    //list product
    @RequestMapping(value = "/list-product")
    public String viewProducts(Model model) {
        model.addAttribute("listProduct", productService.finAll());
        return "list-product";
    }
    //view product by id
    @RequestMapping(value = "/product-view/{id}")
    public String viewProductPage(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product-view";
    }
    //create new product page
    @RequestMapping(value = {"/product-create"}, method = RequestMethod.GET)
    public String createProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "product-create";
    }
    //process save product
    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product, RedirectAttributes redirect) {
        productService.save(product);
        redirect.addFlashAttribute("success", "Save successful!");
        return "redirect:/list-product";
    }
    //update product page
    @RequestMapping(value = "/product-update/{id}")
    public ModelAndView updateProductPage(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("product-update");
        Product product = productService.findById(id);
        mav.addObject("product", product);
        return mav;
    }

    //delete product
    @RequestMapping(value = "/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/list-product";
    }

}
