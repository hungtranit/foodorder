package edu.hcmuaf.food_order.controller;

import edu.hcmuaf.food_order.model.InfoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class SendDataAPI {

    @Autowired
    private HttpSession session;

    private InfoUser infoUserSession;

    @RequestMapping(value = "infoUser", method = RequestMethod.POST)
    public ModelAndView sendInfoUser() {
        ModelAndView mav = new ModelAndView("header");
        mav.addObject("infoUser", infoUserSession);
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
        return "url";
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
}
