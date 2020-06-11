package com.birlasoft.product;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/products")
public class ProductController {
	
	private final ProductRepository productRepository;
	

	
	@Autowired
	public ProductController(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllProducts(){
		try {
			
			
			
			List<Product> productList = productRepository.findAll();
			if(productList.size() != 0) {
				return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);	
			}else
				return new ResponseEntity<String>("No products Available",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	    
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") Integer id){
		try {
			Optional<Product> product = productRepository.findById(id);
			if(product.isPresent() == true) {
				return new ResponseEntity<Product>(product.get(),HttpStatus.OK);	
			}else
				return new ResponseEntity<String>("No product Available for id:"+id,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	    
	}
	
	
	@PostMapping(value="/add")
	public ResponseEntity<?> addProduct(@RequestBody Product product){
		if(Interceptor.authority.equals("ADMIN")) {
		try {

			Product savedProduct = productRepository.saveAndFlush(product);
			if(savedProduct != null) {
				return new ResponseEntity<Product>(product,HttpStatus.OK);	
			}else
				return new ResponseEntity<String>("Product not Added Successsfully",HttpStatus.OK);
		
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	  }else {
		  return new ResponseEntity<String>("Not Authorised",HttpStatus.UNAUTHORIZED);
	  }
	    
	}
}
