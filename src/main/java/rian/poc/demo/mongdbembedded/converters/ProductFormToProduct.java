package rian.poc.demo.mongdbembedded.converters;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import rian.poc.demo.mongdbembedded.commands.ProductForm;
import rian.poc.demo.mongdbembedded.domain.Product;


@Component
public class ProductFormToProduct implements Converter<ProductForm, Product> {

	@Override
	public Product convert(ProductForm productForm) {
		Product product = Product.builder().build();
		if (productForm.getId() != null && !StringUtils.isEmpty(productForm.getId())) {
			product.set_id((new ObjectId(productForm.getId())));
		}
		product.setDescription(productForm.getDescription());
		product.setPrice(productForm.getPrice());
		product.setImageUrl(productForm.getImageUrl());
		return product;
	}
}
