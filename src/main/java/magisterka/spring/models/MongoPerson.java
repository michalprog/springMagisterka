package magisterka.spring.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MongoPerson")
public class MongoPerson {
    @Id
    public String id;          // może być null
    public String name;
    public String surname;
    public int salary;
    public String description; // może być null
    public int role;
}
