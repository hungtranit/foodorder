package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.service.ProductService;
import edu.hcmuaf.food_order.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchAPI {

    public static final int PRODUCT_PER_PAGE = 20;

    @Autowired
    ProductService productService;


    @GetMapping("/search-page")
    public String redirectSearch(@RequestParam(value = "search", required = false) String searchText,
                                 @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                 ModelMap model){
            if(searchText == null && pageNo == null){
                return "search-page";
            }
            if(searchText != null && pageNo == null){
                pageNo = 1;
                model.put("pageNo",1);
            }

            model.addAttribute("resultsCount", productService.searchProductResultCount(searchText));
            model.addAttribute("pageCount", productService.searchProductPageCount(searchText,PRODUCT_PER_PAGE));
            model.addAttribute("productList",productService.searchProuct(searchText,pageNo,PRODUCT_PER_PAGE));

        return "search-page";
    }
}
