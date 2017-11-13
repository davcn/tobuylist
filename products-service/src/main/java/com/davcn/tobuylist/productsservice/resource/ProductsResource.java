package com.davcn.tobuylist.productsservice.resource;

import com.davcn.tobuylist.productsservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsResource {

    RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<Product> getProducts() {
        ResponseEntity<List<Product>> productsResponse = restTemplate.exchange("http://db-service/api/products", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Product>>() {
                });
        return productsResponse.getBody();
    }

    @PostMapping
    public List<Product> create(@RequestBody Product product) {
        return restTemplate.postForObject("http://db-service/api/products", product, List.class);
    }

    // TODO: get products from external API for getting prices and more info
    //@GetMapping
    public List<Product> search(@PathVariable("q") String query) {
        // call external API for searching product information
        return null;
    }
}
