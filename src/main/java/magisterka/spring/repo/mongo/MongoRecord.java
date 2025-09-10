package magisterka.spring.repo.mongo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MongoRecord")
public class MongoRecord {
    @Id
    public String id;
    public String title;
    public String description;
    public double price;
    public boolean active;

    public MongoRecord() {}

    public MongoRecord(String title, String description, double price, boolean active) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.active = active;
    }
}
