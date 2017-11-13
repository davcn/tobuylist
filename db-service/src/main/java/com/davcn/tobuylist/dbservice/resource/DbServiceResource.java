package com.davcn.tobuylist.dbservice.resource;

import com.davcn.tobuylist.dbservice.model.Product;
import com.davcn.tobuylist.dbservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class DbServiceResource {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody final Product product) {
        productRepository.save(product);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteAll() {
        productRepository.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity delete(@PathVariable("product_id") long productId) {
        try {
            productRepository.delete(productId);
        } catch (EmptyResultDataAccessException e) {
            // TODO: show message field and return bad request
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
