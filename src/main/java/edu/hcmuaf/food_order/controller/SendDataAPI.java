package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SendDataAPI {

    @Autowired
    private HttpSession session;

    private InfoUser infoUserSession;

    @Autowired
    HttpSession httpSessionCart;

    List<Item> cart = new ArrayList<>();

    @RequestMapping(value = "infoUser", method = RequestMethod.POST)
    public ModelAndView sendInfoUser() {
        ModelAndView mav = new ModelAndView("header");
        mav.addObject("infoUser", getInfoUserSession());
        return mav;
    }

    @RequestMapping(value = "checkUser", method = RequestMethod.POST)
    public ModelAndView sendCheckUser(String url) {
        ModelAndView mav = new ModelAndView(url);
        mav.addObject("checkUser", infoUserSession);
        return mav;
    }

    public String getPage(Model model, String url) {
        model.addAttribute("infoUser", infoUserSession);
        sendInfoUser();
        return url;
    }

    public ModelAndView sendCart() {
        ModelAndView mav = new ModelAndView("header");
        mav.addObject("qualityProduct", cart.size());
        return mav;
    }

    public ModelAndView sendProductCart() {
        ModelAndView mav = new ModelAndView("shopping-cart");
        mav.addObject("listProductCart", cart);
        mav.addObject("sizeList", cart.size());
        return mav;
    }

    public int sumMoney() {
        int result = 0;
        for (Item item : cart) {
            result += item.getQuantity() * item.getProduct().getPrice();
        }
        return result;
    }

    public ModelAndView sendTotalMoney() {
        ModelAndView mav = new ModelAndView("shopping-cart");
        mav.addObject("totalMoney", sumMoney());
        return mav;
    }

    public SendDataAPI() {
        this.infoUserSession = new InfoUser();
    }

    public InfoUser getInfoUserSession() {
        return infoUserSession;
    }

    public void setInfoUserSession(InfoUser infoUserSession) {
        this.infoUserSession = infoUserSession;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public HttpSession getHttpSessionCart() {
        return httpSessionCart;
    }

    public void setHttpSessionCart(HttpSession httpSessionCart) {
        this.httpSessionCart = httpSessionCart;
    }

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

}
