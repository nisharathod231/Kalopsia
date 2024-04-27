package com.kalopsia.backend.service;

import com.kalopsia.backend.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product addProduct(Product product);

    Product getProduct(int id);
}