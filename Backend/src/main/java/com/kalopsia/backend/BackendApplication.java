package com.kalopsia.backend;

import com.kalopsia.backend.entity.Product;
import com.kalopsia.backend.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		System.out.println("hi you are in main");
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ProductService productService) {
		System.out.println("in commandline runner ___________________________________________________");
		return runner -> {
			addProduct(productService);
		};
	}

	public void addProduct(ProductService productService) {
		System.out.println("adding products............................................................");
		Product product = new Product();
		product.setName("Sapphire Whisper Platinum Ensemble");
		product.setImage("https://whitesatin.ca/wp-content/uploads/2021/11/Sara-Gabriel-Hazel-Bracelet-300x500.webp");
		product.setOldPrice(1200);
		product.setNewPrice(99);
		productService.addProduct(product);
		System.out.println("added......................................................................");
	}

}
