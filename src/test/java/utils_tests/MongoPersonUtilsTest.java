package utils_tests;

import magisterka.spring.models.Statistics;
import magisterka.spring.repo.MongoPersonRepository;
import magisterka.spring.repo.PostPersonRepository;
import magisterka.spring.repo.jpa.PostPerson;
import magisterka.spring.repo.mongo.MongoPerson;
import magisterka.spring.utils.MongoPersonUtils;
import magisterka.spring.utils.PostPersonUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MongoPersonUtilsTest {

    @Mock
    private MongoPersonRepository repository;

    @InjectMocks
    private MongoPersonUtils utils;

    @Test
    void personStatisticsShouldReturnEmptyWhenNoPersons() {
        when(repository.findAll()).thenReturn(List.of());

        assertThat(utils.personStatistics()).isEmpty();
    }

    @Test
    void personStatisticsShouldCalculateAllAndRoleStats() {
        MongoPerson p1 = person("1", "A", "A", 1200, 1);
        MongoPerson p2 = person("2", "B", "B", 800, 1);
        MongoPerson p3 = person("3", "C", "C", 2000, 4);
        when(repository.findAll()).thenReturn(List.of(p1, p2, p3));

        List<Statistics> stats = utils.personStatistics();

        assertThat(stats).hasSize(3);
        assertThat(stats.get(0).role).isEqualTo("all");
        assertThat(stats.get(0).averageSalary).isEqualTo((1200 + 800 + 2000) / 3.0);
        assertThat(stats.get(1).role).isEqualTo("1");
        assertThat(stats.get(1).count).isEqualTo(2);
        assertThat(stats.get(2).role).isEqualTo("4");
        assertThat(stats.get(2).maxSalaryPerson.salary).isEqualTo(2000);
    }

    private static MongoPerson person(String id, String name, String surname, int salary, int role) {
        MongoPerson p = new MongoPerson();
        p.id = id;
        p.name = name;
        p.surname = surname;
        p.salary = salary;
        p.role = role;
        return p;
    }

    @Nested
    @ExtendWith(MockitoExtension.class)
    class PostPersonUtilsTest {

        @Mock
        private PostPersonRepository repository;

        @InjectMocks
        private PostPersonUtils utils;

        @Test
        void personStatisticsShouldReturnEmptyListWhenNoData() {
            when(repository.findAll()).thenReturn(List.of());

            List<Statistics> stats = utils.personStatistics();

            assertThat(stats).isEmpty();
        }

        @Test
        void personStatisticsShouldBuildGlobalAndRoleStatsSorted() {
            PostPerson p1 = person(1, "A", "A", 1000, 0);
            PostPerson p2 = person(2, "B", "B", 2000, 0);
            PostPerson p3 = person(3, "C", "C", 3000, 2);

            when(repository.findAll()).thenReturn(List.of(p1, p2, p3));

            List<Statistics> stats = utils.personStatistics();

            assertThat(stats).hasSize(3);
            assertThat(stats.getFirst().role).isEqualTo("all");
            assertThat(stats.getFirst().count).isEqualTo(3);
            assertThat(stats.getFirst().averageSalary).isEqualTo(2000.0);
            assertThat(stats.get(0).minSalaryPerson.salary).isEqualTo(1000);
            assertThat(stats.get(0).maxSalaryPerson.salary).isEqualTo(3000);

            assertThat(stats.get(1).role).isEqualTo("0");
            assertThat(stats.get(1).count).isEqualTo(2);
            assertThat(stats.get(1).averageSalary).isEqualTo(1500.0);

            assertThat(stats.get(2).role).isEqualTo("2");
            assertThat(stats.get(2).count).isEqualTo(1);
            assertThat(stats.get(2).averageSalary).isEqualTo(3000.0);
        }

        @Test
        void transformPersonsShouldKeepSizeAndRolesAndIncreaseSalary() {
            PostPerson source = person(7, "Old", "Name", 1500, 3);
            source.description = null;

            List<PostPerson> transformed = PostPersonUtils.transformPersons(List.of(source));

            assertThat(transformed).hasSize(1);
            PostPerson out = transformed.getFirst();
            assertThat(out.id).isEqualTo(7);
            assertThat(out.role).isEqualTo(3);
            assertThat(out.salary).isGreaterThanOrEqualTo(2000);
            assertThat(out.description).contains("Lorem ipsum");
            assertThat(out.name).isNotBlank();
            assertThat(out.surname).isNotBlank();
        }

        private static PostPerson person(long id, String name, String surname, int salary, int role) {
            PostPerson p = new PostPerson();
            p.id = id;
            p.name = name;
            p.surname = surname;
            p.salary = salary;
            p.role = role;
            return p;
        }
    }
}