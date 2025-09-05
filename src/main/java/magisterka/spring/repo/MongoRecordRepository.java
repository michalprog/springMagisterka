package magisterka.spring.repo;

import magisterka.spring.models.MongoRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRecordRepository extends MongoRepository<MongoRecord, String> {
}