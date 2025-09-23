package uz.pdp.g56_online_market.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.g56_online_market.entities.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CartDTO {
    private Integer userId;
    private List<CartItemDTO> items;
    private Double totalAmount;
}
