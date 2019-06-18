package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.Product;
import edu.hcmuaf.food_order.repository.ProductRepository;
import edu.hcmuaf.food_order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchAPI {

    public static final int PRODUCT_PER_PAGE = 20;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SendDataAPI sendDataAPI;

    List<Product> list = new ArrayList<>();

    @PostMapping("/search-page")
    public @ResponseBody
    String redirectSearch(@Valid @RequestParam(value = "searchText", required = false) String searchText,
                          @RequestParam(value = "pageNo", required = false) Integer pageNo,
                          ModelMap model) {
        System.out.println("run search");
        if (searchText == "" && pageNo == null) {
            return "search-page";
        }
        if (searchText != "" && pageNo == null) {
            pageNo = 1;
            model.put("pageNo", 1);
        }
        model.addAttribute("resultsCount", productService.searchProductResultCount(searchText));
        model.addAttribute("pageCount", productService.searchProductPageCount(searchText, PRODUCT_PER_PAGE));
        list = productService.searchProuct(searchText, pageNo, PRODUCT_PER_PAGE);
        model.addAttribute("productList", list);
        return "search-page";
    }

    @GetMapping("/search-page/{typeProduct}")
    public String getQuestion(Model model, @PathVariable String typeProduct) {
        list = productRepository.findAllByTypeproduct(typeProduct);
        model.addAttribute("listProduct", list);
        getProduct(typeProduct);
        return "search-page";
    }

    @GetMapping("/search-all")
    public String getSearchAll(Model model) {
        list = productRepository.findAll();
        model.addAttribute("listProduct", list);
        return "search-page";
    }

    private ModelAndView getProduct(String typeProduct) {
        ModelAndView mav = new ModelAndView("search-page");
        mav.addObject("listProduct", productRepository.findAllByTypeproduct(typeProduct));
        return mav;
    }


}
