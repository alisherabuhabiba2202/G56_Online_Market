package uz.pdp.g56_online_market.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;

    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Brands brand;


}
