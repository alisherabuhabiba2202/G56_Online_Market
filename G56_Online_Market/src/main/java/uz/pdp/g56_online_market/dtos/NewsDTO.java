package uz.pdp.g56_online_market.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsDTO {
    private Integer id;
    private String name;
    private String description;
    private String filePath;
}
