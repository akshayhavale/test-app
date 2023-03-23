package com.app.services;

import static java.util.Objects.isNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.dao.Product;
import com.app.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public String save(Product product) {
		Product savedProduct = productRepository.save(product);

		StringBuilder message = new StringBuilder();
		if (isNull(savedProduct)) {
			return message.append("Product not able save due to some issue try again later.").toString();
		}

		return message.append("Product saved successfully with id : ").append(savedProduct.getId()).toString();
	}

	@Override
	public String update(Product product, Long id) throws Exception {
		Product updatableProduct = productRepository.findById(id).orElseThrow(Exception::new);

		if (!StringUtils.isEmpty(product.getProductName())) {
			updatableProduct.setProductName(product.getProductName());
			productRepository.save(updatableProduct);
		}

		return new StringBuilder().append("Update successfull for id :").append(id).toString();
	}

	@Override
	public Product getById(Long id) throws NotFoundException {
		return productRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public String deleteById(Long id) throws NotFoundException {
		Product deletableProduct = productRepository.findById(id).orElseThrow(NotFoundException::new);
		productRepository.delete(deletableProduct);
		return new StringBuilder().append("SuccessFully deleted product for id : ").append(id).toString();
	}

}
