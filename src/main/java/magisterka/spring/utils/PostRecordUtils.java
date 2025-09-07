package magisterka.spring.utils;

import magisterka.spring.models.PostRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import magisterka.spring.repo.PostRecordRepository;
import java.util.List;

@Service
public class PostRecordUtils {
    @Autowired
    private static PostRecordRepository repository;
    public PostRecordUtils(PostRecordRepository repository) {
        this.repository = repository;
    }

    public static List<PostRecord> getALL() {
        return repository.findAll();
    }

    public static List<PostRecord> getRecords(int limit) {
        return repository.findAll().stream().limit(limit).toList();
    }

    public static List<PostRecord> createRecords(List<PostRecord> records) {
        return repository.saveAll(records);
    }

    public static List<PostRecord> updateRecords(List<PostRecord> records) {
        return repository.saveAll(records);
    }

    public static int deleteRecords(int limit) {
        List<PostRecord> recordsToDelete = repository.findAll().stream().limit(limit).toList();
        repository.deleteAll(recordsToDelete);
        return recordsToDelete.size();
    }
}
