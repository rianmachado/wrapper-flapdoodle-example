package rian.poc.demo.mongdbembedded.services;

import java.util.List;

import rian.poc.demo.mongdbembedded.commands.ProductForm;
import rian.poc.demo.mongdbembedded.domain.Product;

public interface ProductService {

	List<Product> listAll();

	Product getById(String id);

	Product saveOrUpdate(Product product);

	void delete(String id);

	Product saveOrUpdateProductForm(ProductForm productForm);
}
