package com.example.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String openHomePage() {
        return "index";
    }

    @GetMapping("/menu")
    public String openMenuPage() {
        return "menu";
    }

    @GetMapping("/banquet")
    public String openBanquetPage() {
        return "banquet";
    }
    @GetMapping("/about")
    public String openAboutPage() {
        return "about";
    }
    @GetMapping("/discount")
    public String openDiscountPage() {
        return "discount";
    }
}
