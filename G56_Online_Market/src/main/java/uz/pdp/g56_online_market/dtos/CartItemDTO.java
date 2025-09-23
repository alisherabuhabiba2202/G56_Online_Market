package uz.pdp.g56_online_market.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CartItemDTO {
    private Integer productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private Double totalPrice;
}
