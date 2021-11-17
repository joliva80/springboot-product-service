package com.springboot.app.product.model.service;

import java.util.List;

import com.springboot.app.commonslib.model.entity.Product;

public interface IProductService {
    
    public List<Product> findAll();
    public Product findById(Long id);
    public Product save(Product product);
    public void deleteById(Long id);
}
