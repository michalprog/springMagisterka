package magisterka.spring.utils;

import magisterka.spring.repo.mongo.MongoRecord;
import magisterka.spring.repo.MongoRecordRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
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
        List<MongoRecord> records = mongoTemplate.find(query, MongoRecord.class);

        int deletedCount = 0;
        for (MongoRecord record : records) {
            try {
                repository.delete(record);
                deletedCount++;
            } catch (Exception ignored) {
            }
        }
        return deletedCount;
    }
}
