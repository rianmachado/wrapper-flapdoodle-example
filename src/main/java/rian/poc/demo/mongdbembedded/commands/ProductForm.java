package rian.poc.demo.mongdbembedded.commands;


import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {
    private String id;
    private String description;
    private BigDecimal price;
    private String imageUrl;

}
