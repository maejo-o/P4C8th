package com.jisu.log4shellserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    Logger log = LoggerFactory.getLogger(HomeController.class);
    @RequestMapping("/")
    public String index() {
        log.debug("index! name:{}", name);;
        return "index";
    }

}
