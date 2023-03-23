package com.app.services;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.Product;

@Service
public interface ProductService {
	
	public String save(Product product);
	public String update(Product product,Long id) throws Exception;
	public Product getById(Long id) throws NotFoundException;
	public List<Product> getAll();
	public String deleteById(Long id) throws NotFoundException;

}
