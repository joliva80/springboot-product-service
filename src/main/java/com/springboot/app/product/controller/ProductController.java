package com.springboot.app.product.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.springboot.app.commonslib.model.entity.Product;
import com.springboot.app.product.model.service.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        Product newProduct = productService.save(product);
        return newProduct;
    }


    @PutMapping("products/{id}")
    public Product update(@RequestBody Product product, @PathVariable Long id)
    {
        // First check if product identified by {id} already exists or not
        Product productDb = productService.findById(id);

        // Then update the fields
        productDb.setName( product.getName() );
        productDb.setPrice( product.getPrice() );

        // Finally save the updated product
        Product newProduct = productService.save(productDb);

        return newProduct;
    }


    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productService.deleteById(id);
    }
}
