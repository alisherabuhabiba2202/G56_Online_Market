package uz.pdp.g56_online_market.services;

import uz.pdp.g56_online_market.daos.ProductDAO;
import uz.pdp.g56_online_market.dtos.ProductDTO;
import uz.pdp.g56_online_market.entities.Products;
import uz.pdp.g56_online_market.mappers.ProductMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();

    public List<ProductDTO>  getProductsByPageable(int page, int size) {
        List<Products> products = productDAO.getProductsByPageable(page, size);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Products product : products) {
            productDTOS.add(new ProductDTO().builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .filePath(product.getFilePath())
                    .brand(product.getBrand())
                    .build());
        }
        productDTOS.forEach(item ->{
            item.setQuantity(productDAO.getProductQuatityById(item.getId()));
        });
        return productDTOS;
    }

    public Products getProductById(int id) {
        return productDAO.findById(id);
    }

}
