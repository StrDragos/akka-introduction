package com.dragos.presentation.controller;

import com.dragos.presentation.services.ParseFrase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.function.Supplier;

@Controller
public class HomeController {

    @Autowired
    private ParseFrase parseFrase;

    private Supplier<HomePageModel> modelSupplier = HomePageModel::new;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getHomePage(@ModelAttribute("pageModel") HomePageModel model) throws IOException {
        ModelAndView modelAndView = new ModelAndView("homepage");
        modelAndView.addObject(modelSupplier.get());
        return modelAndView;
    }

    @RequestMapping(value = "/countwords", method = RequestMethod.POST)
    public ModelAndView askForCount(@ModelAttribute("pageModel") HomePageModel model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        String toBeParsed = model.getCountFor();
        parseFrase.parse(toBeParsed);
        modelSupplier.get().setCountFor(model.getCountFor());
        return modelAndView;
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public ModelAndView askForResults() {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        modelSupplier.get().setCountedWords("counted words");
        return modelAndView;
    }
}
