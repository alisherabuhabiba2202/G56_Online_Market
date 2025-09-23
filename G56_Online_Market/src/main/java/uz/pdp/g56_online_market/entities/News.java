package uz.pdp.g56_online_market.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String filePath;
    @Column()
    private byte[] photo;

    public News(String title, String description, byte[] photo) {
        this.title = title;
        this.description = description;
        this.photo = photo;
    }

    public News(Integer id) {
        this.id = id;
    }
}
