package com.example.springsecirityapplication.services;

import com.example.springsecirityapplication.models.Product;
import com.example.springsecirityapplication.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductId(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Transactional
    public void saveProduct(Product product){
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(int id, Product product){
        product.setId(id);
//        product.setCategory(category);
        productRepository.save(product); // update user_site SET last_Name = user.last_Name where id = параметру id
    }
    @Transactional
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }


}
