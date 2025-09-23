package uz.pdp.g56_online_market.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data

public class CartItems extends AuditEntity{

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Products products;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    private Integer quantity;

}
