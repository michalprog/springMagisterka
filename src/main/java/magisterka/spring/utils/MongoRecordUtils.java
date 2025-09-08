package magisterka.spring.utils;

import magisterka.spring.models.MongoRecord;
import magisterka.spring.repo.MongoRecordRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoRecordUtils {

    private final MongoRecordRepository repository;

    public MongoRecordUtils(MongoRecordRepository repository) {
        this.repository = repository;
    }

    public List<MongoRecord> getALL() {
        return repository.findAll();
    }

    public List<MongoRecord> getRecords(int limit) {
        return repository.findAll(PageRequest.of(0, limit)).getContent();
    }


    public List<MongoRecord> createRecords(List<MongoRecord> records) {
        return repository.saveAll(records);
    }


    public List<MongoRecord> updateRecords(List<MongoRecord> records) {
        return repository.saveAll(records);
    }


    public int deleteRecords(int limit) {
        List<MongoRecord> toDelete = getRecords(limit);
        repository.deleteAll(toDelete);
        return toDelete.size();
    }
}
