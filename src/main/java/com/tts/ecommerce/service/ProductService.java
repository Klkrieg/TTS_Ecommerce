package com.tts.ecommerce.service;

import com.tts.ecommerce.model.Product;
import com.tts.ecommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
  @Autowired
  ProductRepo productRepo;

  public List<Product> findAll(){
    return productRepo.findAll();
  }

  public Product findById(long id){
    return productRepo.findById(id);
  }

  public List<String> findDistinctBrands(){
    return productRepo.findDistinctBrands();
  }

  public List<String> findDistinctCategories(){
    return productRepo.findDistinctCategories();
  }

  public void save(Product product){
    productRepo.save(product);
  }

  public void deleteById(long id){
    productRepo.deleteById(id);
  }

  public List<Product> findByBrandAndOrCategory(String brand, String category){
    if(category == null && brand == null){
      return productRepo.findAll();
    }else if(category == null){
      return productRepo.findByBrand(brand);
    }else if(brand == null){
      return productRepo.findByCategory(category);
    }else{
      return productRepo.findByBrandAndCategory(brand, category);
    }
  }
}
