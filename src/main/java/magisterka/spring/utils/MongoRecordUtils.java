package magisterka.spring.utils;

import com.mongodb.client.result.DeleteResult;
import magisterka.spring.repo.MongoRecordRepository;
import magisterka.spring.repo.mongo.MongoRecord;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoRecordUtils {

    private final MongoRecordRepository repository;
    private final MongoTemplate mongoTemplate;

    public MongoRecordUtils(MongoRecordRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<MongoRecord> getALL() {
        return repository.findAll();
    }

    public List<MongoRecord> getRecords(int limit) {
        Query query = new Query().limit(limit);
        return mongoTemplate.find(query, MongoRecord.class);
    }

    public List<MongoRecord> createRecords(List<MongoRecord> records) {
        return repository.saveAll(records);
    }

    public List<MongoRecord> updateRecords(List<MongoRecord> records) {
        return repository.saveAll(records);
    }

    public int deleteRecords(int limit) {

        Query query = new Query().limit(limit);
        query.fields().include("_id"); // tylko ID, nie cały dokument

        List<MongoRecord> records = mongoTemplate.find(query, MongoRecord.class);
        List<String> ids = records.stream()
                .map(r -> r.id)  // bez gettera, bo pole jest publiczne
                .toList();

        if (ids.isEmpty()) return 0;
        Query deleteQuery = new Query(Criteria.where("_id").in(ids));
        DeleteResult result = mongoTemplate.remove(deleteQuery, MongoRecord.class);

        return (int) result.getDeletedCount();
    }
}
