package magisterka.spring.utils;

import magisterka.spring.repo.jpa.PostRecord;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import magisterka.spring.repo.PostRecordRepository;
import java.util.List;

@Service
public class PostRecordUtils {
    private final PostRecordRepository repository;
    public PostRecordUtils(PostRecordRepository repository) {
        this.repository = repository;
    }

    public List<PostRecord> getALL() {
        return repository.findAll();
    }

    public List<PostRecord> getRecords(int limit) {
        return repository.findAll(
                PageRequest.of(0, limit, Sort.by(Sort.Direction.ASC, "id"))
        ).getContent();

    }

    public List<PostRecord> createRecords(List<PostRecord> records) {
        return repository.saveAll(records);
    }

    public List<PostRecord> updateRecords(List<PostRecord> records) {
        return repository.saveAll(records);
    }

    public int deleteRecords(int limit) {
        List<PostRecord> recordsToDelete = repository.findAll(
                PageRequest.of(0, limit, Sort.by(Sort.Direction.ASC, "id"))
        ).getContent();

        repository.deleteAll(recordsToDelete);
        return recordsToDelete.size();
    }
}