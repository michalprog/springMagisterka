package magisterka.spring.models;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
@Entity
@Table(name = "records")
public class PostRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false)
    public String title;

    public String description;

    @Column(nullable = false)
    public double price;

    @Column(nullable = false)
    public boolean active;
}

