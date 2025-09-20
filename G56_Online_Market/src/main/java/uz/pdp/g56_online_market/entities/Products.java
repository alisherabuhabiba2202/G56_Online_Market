package uz.pdp.g56_online_market.entities;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class Products extends AuditEntity{

    private String name;
    private String description;
    private Double price;
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Brands brand;
}
