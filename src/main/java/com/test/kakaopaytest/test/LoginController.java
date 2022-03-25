package com.test.kakaopaytest.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class LoginController {
    final private static Logger LOG = Logger.getGlobal();

    @GetMapping("/api/login")
    public void login() {
        LOG.info("GET successfully called on /login resource");
    }

}
