package com.hcaglar.implicitamingstrategy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 8.03.2022
 */
@Controller
public class IndexController {

    @GetMapping
    public String index(){
        return "redirect:/swagger-ui.html";
    }
}
