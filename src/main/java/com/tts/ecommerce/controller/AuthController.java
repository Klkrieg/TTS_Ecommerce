package com.tts.ecommerce.controller;

import com.tts.ecommerce.model.User;
import com.tts.ecommerce.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

@Controller
public class AuthController {
  @Autowired
  private UserService userService;
  @GetMapping("/login")
  public String login(){
    return "login";
  }

  @SneakyThrows
  @PostMapping("/login")
  public String signUp(@Valid User user,
                       @RequestParam String submit,
                       BindingResult bindingResult,
                       HttpServletRequest request) throws SerialException {
    String password = user.getPassword();
    if(submit.equals("up")){
      if(userService.findByUsername(user.getUsername()) == null){
        userService.saveNew(user);
      }else{
        bindingResult.rejectValue("username", "user.error", "Username is already taken");
        return "login";
      }
    }
    request.login(user.getUsername(), password);
    return "redirect:/";
  }
}
