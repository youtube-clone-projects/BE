package com.sparta.akijaki.config.auth.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @GetMapping("/social")
    public String socialSuccess(Model model,
        @RequestParam(value = "provider", required = false) String provider,
        @RequestParam(value = "oauthId", required = false) String oauthId
    ) {
        model.addAttribute("provider", provider);
        model.addAttribute("oauthId", oauthId);
        return "social-success";
    }

    @GetMapping("/hello")
    public String hello() {
        return "index";
    }
}