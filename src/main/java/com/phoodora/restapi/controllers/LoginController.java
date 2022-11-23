package com.phoodora.restapi.controllers;

import com.phoodora.restapi.models.Users;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.phoodora.restapi.security.UserDetails;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LoginController {
    
    @Autowired
    private UserDetails userDetailsService;

    @PostMapping("/register/customer")
    public void addCustomer(@RequestBody JSONObject input) {
      String username = (String) input.get("username");
      String password = (String) input.get("password");

         Users user = new Users();
         user.setUsername(username); 
         user.setPassword(password);
         userDetailsService.createUser(user, "CUSTOMER");
   }

    @PostMapping("/register/manager")
    public void addManager(@RequestBody JSONObject input) {
      String username = (String) input.get("username");
      String password = (String) input.get("password");

         Users user = new Users();
         user.setUsername(username); 
         user.setPassword(password);
         userDetailsService.createUser(user, "MANAGER");
   }
}
