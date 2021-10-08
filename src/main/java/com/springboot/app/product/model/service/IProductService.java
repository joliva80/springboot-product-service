package com.springboot.app.product.model.service;

import java.util.List;

import com.springboot.app.product.model.entity.Product;

public interface IProductService {
    
    public List<Product> findAll();
    public Product findById(Long id);
}
