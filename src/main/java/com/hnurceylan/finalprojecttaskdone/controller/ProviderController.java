package com.hnurceylan.finalprojecttaskdone.controller;


import com.hnurceylan.finalprojecttaskdone.entities.User;
import com.hnurceylan.finalprojecttaskdone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provider")
@CrossOrigin(origins = "http://localhost:3000")
public class ProviderController {

    @Autowired
    private UserService userService;

    // Onaylı providerları dönen endpoint
    @GetMapping("/approved-providers")
    public List<User> getApprovedProviders() {
        return userService.getApprovedProviders();
    }
}

