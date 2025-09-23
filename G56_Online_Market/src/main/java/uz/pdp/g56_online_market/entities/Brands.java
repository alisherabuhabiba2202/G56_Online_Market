package uz.pdp.g56_online_market.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "brands")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class Brands{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique=true)
    private String name;
    @Column(nullable = false)
    private String filepath;
}
