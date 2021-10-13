package com.springboot.app.product.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.springboot.app.product.model.entity.Product;
import com.springboot.app.product.model.service.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ProductController {
    
    @Autowired
    private IProductService productService;

    //@Autowired
    //private Environment environment;

    @Value("${server.port}") // Get environment value from application.configuration file
    private Integer port;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.findAll().stream().map(p -> {
            //p.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            p.setPort(port);
            return p;
        }).collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) throws InterruptedException{

        // Error simulation
        if(id.equals(10L)){
            throw new IllegalStateException("Product not found.");
        }
        // Timeout simulation
        if(id.equals(7L)){
            TimeUnit.SECONDS.sleep(6L);
        }

        Product product = productService.findById(id);
        //product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        product.setPort(port);
        return product;
    }
}
