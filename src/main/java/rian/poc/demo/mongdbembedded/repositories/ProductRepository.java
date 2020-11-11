package rian.poc.demo.mongdbembedded.repositories;
import org.springframework.data.repository.CrudRepository;

import rian.poc.demo.mongdbembedded.domain.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
	Product findBydescription(String description);
}
