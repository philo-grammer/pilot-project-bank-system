package com.woowahan.biz.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by sykim on 2016. 4. 10..
 */
@RestController
@RequestMapping("home")
public class HomeController {

    @RequestMapping(method = GET)
    public String home(Model model) {
        return "home";
    }
}
