package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import edu.hcmuaf.food_order.model.Item;
import edu.hcmuaf.food_order.model.Product;
import edu.hcmuaf.food_order.repository.ProductRepository;
import edu.hcmuaf.food_order.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    SendDataAPI sendDataAPI;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/shopping-cart")
    public String getShopCart(Model model) {
        sendDataAPI.sendInfoUser();
        sendDataAPI.getSession().getAttribute("infoUser");
        sendInfoUser();
        sendDataAPI.getPage(model, "shopping-cart");
        sendDataShoppingCart(model);
        model.addAttribute("sizeList", sendDataAPI.getCart().size());
        return "shopping-cart";
    }

    private ModelAndView sendInfoUser() {
        ModelAndView mav = new ModelAndView("shopping-cart");
        mav.addObject("infoUser", sendDataAPI.getInfoUserSession());
        return mav;
    }

    @GetMapping("/shopping-cart/add/{productID}")
    public String addToShoppingCart(Model model, @PathVariable int productID) {
        sendDataAPI.sendInfoUser();
        sendDataAPI.getSession().getAttribute("infoUser");
        sendInfoUser();
        sendDataAPI.getPage(model, "shopping-cart");
        if (!isExisted(productID, sendDataAPI.getCart())) {
            Item item = new Item(productRepository.getOne(productID), 1);
            sendDataAPI.getCart().add(item);
        } else {
            sendDataAPI.getCart().get(getIndexProductOfList(productID, sendDataAPI.getCart()))
                    .setQuantity(sendDataAPI.getCart().get(getIndexProductOfList(productID, sendDataAPI.getCart()))
                            .getQuantity() + 1);
        }
        sendDataAPI.getHttpSessionCart().setAttribute("cart", sendDataAPI.getCart());
        sendDataShoppingCart(model);
        return "shopping-cart";
    }

    @GetMapping("/shopping-cart/remove/{productID}")
    public String removeProduct(Model model, @PathVariable int productID) {
        int index = getIndexProductOfList(productID, sendDataAPI.getCart());
        sendDataAPI.getCart().remove(index);
        sendDataShoppingCart(model);
        model.addAttribute("sizeList", sendDataAPI.getCart().size());
        return "shopping-cart";
    }

    private void sendDataShoppingCart(Model model) {
        sendDataAPI.sendInfoUser();
        sendDataAPI.getSession().getAttribute("infoUser");
        sendInfoUser();
        sendDataAPI.getPage(model, "shopping-cart");
        model.addAttribute("detailUser", sendDataAPI.getInfoUserSession());
        model.addAttribute("qualityProduct", sendDataAPI.getCart().size());
        sendDataAPI.sendCart();
        model.addAttribute("listProductCart", sendDataAPI.getCart());
        model.addAttribute("sizeList", sendDataAPI.getCart().size());
        sendDataAPI.sendProductCart();
        model.addAttribute("totalMoney", sendDataAPI.sumMoney());
        sendDataAPI.sendTotalMoney();
    }

    private boolean isExisted(int id, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getPostid() == id) {
                return true;
            }
        }
        return false;
    }

    private int getIndexProductOfList(int productID, List<Item> cart) {
        int result = -1;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getPostid() == productID) {
                result = i;
            }
        }
        return result;
    }

}
