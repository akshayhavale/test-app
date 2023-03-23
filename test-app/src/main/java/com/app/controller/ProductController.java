package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.configuration.ConnectionConfiguration;
import com.app.dao.Product;
import com.app.services.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ConnectionConfiguration connectionConfiguration;

	@PostMapping(value = "/save")
	public @ResponseBody String save(@RequestBody Product product, HttpServletRequest request) {
		return productService.save(product);
	}

	@GetMapping(value = "/{id}")
	public @ResponseBody Product getProductById(@PathVariable(value = "id", required = true) Long id)
			throws NotFoundException {
		return productService.getById(id);
	}

	@GetMapping(value = "/list")
	public @ResponseBody List<Product> getAllProducts() {
		return productService.getAll();
	}

	@DeleteMapping(value = "/{id}")
	public @ResponseBody String deleteById(@PathVariable(value = "id", required = true) Long id)
			throws NotFoundException {
		return productService.deleteById(id);
	}

	@GetMapping("/config")
	public void printConConfigProperties() {

		System.out.println("*********** Verifying configuration properties ***********");
		System.out.println("name " + connectionConfiguration.getName());
		System.out.println("baseurl " + connectionConfiguration.getBaseurl());
		System.out.println("username " + connectionConfiguration.getUsername());
		System.out.println("password " + connectionConfiguration.getPassword());
		System.out.println("***********END***************");
	}

	@PutMapping("/update/{id}")
	public @ResponseBody String updateProduct(@RequestBody Product product,
			@PathVariable(value = "id", required = true) Long id) throws Exception {
		return productService.update(product, id);
	}

}
