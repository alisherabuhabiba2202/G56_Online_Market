package uz.pdp.g56_online_market.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandDTO {
    private Integer Id;
    private String name;
    private String filepath;
    private int quantity;
}
