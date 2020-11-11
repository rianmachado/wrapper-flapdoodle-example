package rian.poc.demo.mongdbembedded.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rian.poc.demo.mongdbembedded.commands.ProductForm;
import rian.poc.demo.mongdbembedded.converters.ProductToProductForm;
import rian.poc.demo.mongdbembedded.domain.Product;
import rian.poc.demo.mongdbembedded.services.ProductService;

@Controller
public class ProductController {
	private ProductService productService;

	private ProductToProductForm productToProductForm;

	@Autowired
	public void setProductToProductForm(ProductToProductForm productToProductForm) {
		this.productToProductForm = productToProductForm;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping("/product/show/{id}")
	public ResponseEntity<ProductForm> getProduct(@PathVariable String id) {
		Product product = productService.getById(id);
		if (product == null) {
			return new ResponseEntity<ProductForm>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(productToProductForm.convert(product));
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public ResponseEntity<ProductForm> saveOrUpdateProduct(@Valid @RequestBody ProductForm productForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<ProductForm>(HttpStatus.BAD_REQUEST);
		}
		Product product = productService.saveOrUpdateProductForm(productForm);
		return new ResponseEntity<ProductForm>(productToProductForm.convert(product), HttpStatus.CREATED);
	}

	@RequestMapping("/product/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		productService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
