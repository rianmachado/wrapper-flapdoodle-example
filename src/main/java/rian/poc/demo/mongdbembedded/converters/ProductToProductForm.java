package rian.poc.demo.mongdbembedded.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rian.poc.demo.mongdbembedded.commands.ProductForm;
import rian.poc.demo.mongdbembedded.domain.Product;

@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {
    @Override
    public ProductForm convert(Product product) {
        ProductForm productForm = new ProductForm();
        productForm.setId(product.get_id().toHexString());
        productForm.setDescription(product.getDescription());
        productForm.setPrice(product.getPrice());
        productForm.setImageUrl(product.getImageUrl());
        return productForm;
    }
}
