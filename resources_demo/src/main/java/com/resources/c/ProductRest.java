
package com.resources.c;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resources.m.Product;

@RestController                        
@RequestMapping 

public class ProductRest {

	@GetMapping("name")
	public ResponseEntity<Product> getProduct(){
		Product product = new Product();
		product.setName("Hello at-resources-api");
		return ResponseEntity.ok(product);	
	}
}