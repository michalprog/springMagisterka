package magisterka.spring.repo;

import magisterka.spring.repo.mongo.MongoRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MongoRecordRepository extends MongoRepository<MongoRecord, String> {
    @Query("{}")
    List<MongoRecord> findLimited(org.springframework.data.domain.Pageable pageable);
}