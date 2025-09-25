package uz.pdp.g56_online_market.services;

import uz.pdp.g56_online_market.daos.ProductDAO;
import uz.pdp.g56_online_market.dtos.ProductDTO;
import uz.pdp.g56_online_market.entities.Brands;
import uz.pdp.g56_online_market.entities.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();

    public List<ProductDTO>  getProductsByPageable(int page, int size) {
        List<Products> products = productDAO.getProductsByPageable(page, size);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Products product : products) {
            productDTOS.add(ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .filePath(product.getFilePath())
                    .brand(product.getBrand())
                    .build());
        }
        productDTOS.forEach(item ->{
            item.setQuantity(productDAO.getProductQuantityById(item.getId()));
        });
        return productDTOS;
    }

    public void addProduct(String name, String desc, Double price, String filePath, Brands brand) {
        Products p = Products.builder()
                .name(name)
                .description(desc)
                .price(price)
                .filePath(filePath)
                .brand(brand)
                .build();
        productDAO.save(p);
    }

    public void updateProduct(int id, String name, String desc, Double price, String filePath, Brands brand) {
        Products p = productDAO.findById(id);
        if (p != null) {
            p.setName(name);
            p.setDescription(desc);
            p.setPrice(price);
            p.setFilePath(filePath);
            p.setBrand(brand);
            productDAO.update(p);
        }
    }

    public void deleteProduct(int id) {
        productDAO.delete(id);
    }

    public Products getById(int id) {
        return productDAO.findById(id);
    }

    public List<Products> getAllProducts() {
        return productDAO.getAllProducts();
    }

}
