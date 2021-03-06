package com.tts.ecommerce.controller;

import com.tts.ecommerce.model.User;
import com.tts.ecommerce.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

@Controller
public class AuthController {
  @Autowired
  private UserService userService;
  @GetMapping("/signin")
  public String login(){
    return "signin";
  }

  //@SneakyThrows
  @PostMapping("/signin")
  public String signup(@Valid User user, @RequestParam String submit, BindingResult bindingResult,
                       HttpServletRequest request) throws ServletException {
    String password = user.getPassword();
//    System.out.println("----------------------" + request);

    if (submit.equals("up")) {
      if (userService.findByUsername(user.getUsername()) == null) {
        userService.saveNew(user);
        request.login(user.getUsername(), password);
        return "redirect:/";
      } else {
        bindingResult.rejectValue("username", "error.user", "Username is already taken.");
        return "signin";
      }
    }
    return "signin";
  }
}
