package uz.pdp.g56_online_market.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

//@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class Brands{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
