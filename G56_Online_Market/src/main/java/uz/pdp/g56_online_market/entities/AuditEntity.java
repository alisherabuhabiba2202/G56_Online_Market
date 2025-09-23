package uz.pdp.g56_online_market.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   @Column(nullable = false, updatable = false)
    private Timestamp createdAt;
   private Timestamp updatedAt;
}
