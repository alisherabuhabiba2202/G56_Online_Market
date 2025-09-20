package uz.pdp.g56_online_market.mappers;

import uz.pdp.g56_online_market.dtos.ProductDTO;
import uz.pdp.g56_online_market.entities.Products;

public class ProductMapper {

    public static ProductDTO productToProductDTO(Products products) {
        return new ProductDTO().builder()
                .id(products.getId())
                .name(products.getName())
                .description(products.getDescription())
                .price(products.getPrice())
                .filePath(products.getFilePath())
                .brand(products.getBrand())
                .build();
    }

}
