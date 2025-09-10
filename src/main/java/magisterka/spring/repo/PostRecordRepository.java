package magisterka.spring.repo;

import magisterka.spring.repo.jpa.PostRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRecordRepository extends JpaRepository<PostRecord, Integer> {
}