package magisterka.spring.repo.jpa;
import jakarta.persistence.*;

@Entity
@Table(name = "person") // nazwa tabeli w bazie
public class PostPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(nullable = false, unique = true)
    public long id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String surname;

    @Column(nullable = false)
    public int salary;

    @Column(nullable = true) // może być null
    public String description;

    @Column(nullable = false)
    public int role;
}
