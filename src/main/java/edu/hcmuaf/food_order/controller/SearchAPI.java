package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.repository.ProductRepository;
import edu.hcmuaf.food_order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchAPI {

    public static final int PRODUCT_PER_PAGE = 20;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SendDataAPI sendDataAPI;

    @GetMapping("/search-page")
    public String redirectSearch(@RequestParam(value = "search", required = false) String searchText,
                                 @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                 ModelMap model) {
//        ModelMap model, Model modelGetPage) {
//        sendInfoUser(modelGetPage);
        if (searchText == null && pageNo == null) {
            return "search-page";
        }
        if (searchText != null && pageNo == null) {
            pageNo = 1;
            model.put("pageNo", 1);
        }
        model.addAttribute("resultsCount", productService.searchProductResultCount(searchText));
        model.addAttribute("pageCount", productService.searchProductPageCount(searchText, PRODUCT_PER_PAGE));
        model.addAttribute("productList", productService.searchProuct(searchText, pageNo, PRODUCT_PER_PAGE));
        return "search-page";
    }

//    private ModelAndView sendInfoUser(Model model) {
//        ModelAndView mav = new ModelAndView("search-page");
//        mav.addObject("infoUser", sendDataAPI.getInfoUserSession());
//        sendDataAPI.sendInfoUser();
//        sendDataAPI.getSession().getAttribute("infoUser");
//        sendDataAPI.getPage(model, "search-page");
//        return mav;
//    }

    @GetMapping("/search-page/{typeProduct}")
    public String getQuestion(Model model, @PathVariable String typeProduct) {
        model.addAttribute("listProduct", productRepository.findAllByTypeproduct(typeProduct));
        getProduct(typeProduct);
//        sendInfoUser(model);
        return "search-page";
    }

    private ModelAndView getProduct(String typeProduct) {
        ModelAndView mav = new ModelAndView("search-page");
        mav.addObject("listProduct", productRepository.findAllByTypeproduct(typeProduct));
        return mav;
    }


}
