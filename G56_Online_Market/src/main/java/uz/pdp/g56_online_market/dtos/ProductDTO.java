package uz.pdp.g56_online_market.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.g56_online_market.entities.Brands;
import uz.pdp.g56_online_market.entities.CartItems;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String filePath;
    private int quantity;
    private Brands brand;

}
