package magisterka.spring.repo;

import magisterka.spring.repo.jpa.PostPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostPersonRepository extends JpaRepository<PostPerson, Long> {
}