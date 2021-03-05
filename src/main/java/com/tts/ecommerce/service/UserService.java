package com.tts.ecommerce.service;

import com.tts.ecommerce.model.Product;
import com.tts.ecommerce.model.User;
import com.tts.ecommerce.repository.ProductRepo;
import com.tts.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserService implements UserDetailsService {
  @Autowired
  UserRepo userRepo;
  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  public User findByUsername(String username){
    return userRepo.findByUsername(username);
  }

  public void saveNew(User user){
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userRepo.save(user);
  }
  public void saveExsisting(User user){
    userRepo.save(user);
  }
  public User getLoggedInUser(){
    return findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
  }
  public void updateCart(Map<Product, Integer> cart){
    User user = getLoggedInUser();
    user.setCart(cart);
    saveExsisting(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = findByUsername(username);
    if(user == null){
      throw new UsernameNotFoundException("Username Not Found");
    }
    return user;
  }
}


















