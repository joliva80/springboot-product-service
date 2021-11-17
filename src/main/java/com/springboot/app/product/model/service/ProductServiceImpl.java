package com.springboot.app.product.model.service;

import java.util.List;

import com.springboot.app.product.model.dao.ProductDao;
import com.springboot.app.commonslib.model.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productDao.findById(id).orElse(null); // return a get of the product or a null if any exception is thrown
    }

    /**
     * Create or update the entity Product just in case this already exists
     * or not.
     */
    @Override
    @Transactional
    public Product save(Product product) {
        Product newProduct = productDao.save(product);
        return newProduct;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }
    
}
