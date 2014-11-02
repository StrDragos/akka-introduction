package com.dragos.presentation.controller;

import com.dragos.presentation.services.ParseFrase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class HomeController {

    @Autowired
    private ParseFrase parseFrase;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getHomePage() throws IOException {
        ModelAndView modelAndView = new ModelAndView("homepage");
        modelAndView.addObject("pageModel", new HomePageModel());
        return modelAndView;
    }

    @RequestMapping(value = "/countwords", method = RequestMethod.POST)
    public ModelAndView askForCount(@ModelAttribute("pageModel") HomePageModel model) {
        parseFrase.parse(model.getCountFor());
        ModelAndView redirect = new ModelAndView("homepage");
        redirect.addObject("pageModel", model);
        return redirect;
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public ModelAndView askForResults(@ModelAttribute("pageModel") HomePageModel model) throws Exception {
        model.setCountedWords(parseFrase.askForResults());
        ModelAndView modelAndView = new ModelAndView("homepage");
        modelAndView.addObject("pageModel", model);
        return modelAndView;
    }
}
