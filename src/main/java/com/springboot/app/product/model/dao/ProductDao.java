package com.springboot.app.product.model.dao;

import com.springboot.app.product.model.entity.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product, Long>{
    
}
