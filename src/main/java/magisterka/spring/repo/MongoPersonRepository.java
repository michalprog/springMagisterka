package magisterka.spring.repo;

import magisterka.spring.models.MongoPerson;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoPersonRepository extends MongoRepository<MongoPerson, String> {
}
