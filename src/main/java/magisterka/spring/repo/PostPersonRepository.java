package magisterka.spring.repo;

import magisterka.spring.models.PostPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostPersonRepository extends JpaRepository<PostPerson, Long> {
}