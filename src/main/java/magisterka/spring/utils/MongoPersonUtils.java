package magisterka.spring.utils;

import magisterka.spring.repo.mongo.MongoPerson;
import magisterka.spring.models.Statistics;
import magisterka.spring.models.UniversalPerson;
import magisterka.spring.repo.MongoPersonRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MongoPersonUtils {

    private final MongoPersonRepository repository;

    public MongoPersonUtils(MongoPersonRepository repository) {
        this.repository = repository;
    }

    public List<MongoPerson> getAll() {
        return repository.findAll();
    }

    public List<MongoPerson> createPersons(List<MongoPerson> persons) {
        return repository.saveAll(persons);
    }

    public List<Statistics> personStatistics() {
        List<MongoPerson> persons = repository.findAll();

        if (persons.isEmpty()) {
            return Collections.emptyList();
        }

        // Grupowanie po roli
        Map<Integer, List<MongoPerson>> groupedByRole = persons.stream()
                .collect(Collectors.groupingBy(p -> p.role));

        List<Statistics> stats = new ArrayList<>();

        // 📌 Statystyki ogólne ("all")
        int totalAll = persons.stream().mapToInt(p -> p.salary).sum();
        double avgAll = totalAll / (double) persons.size();
        MongoPerson minAll = persons.stream().min(Comparator.comparingInt(p -> p.salary)).get();
        MongoPerson maxAll = persons.stream().max(Comparator.comparingInt(p -> p.salary)).get();

        stats.add(new Statistics(
                "all",
                persons.size(),
                avgAll,
                new UniversalPerson(minAll.name, minAll.surname, minAll.salary, minAll.role),
                new UniversalPerson(maxAll.name, maxAll.surname, maxAll.salary, maxAll.role)
        ));

        // 📌 Statystyki per rola
        for (int role = 0; role <= 4; role++) {
            List<MongoPerson> roleGroup = groupedByRole.getOrDefault(role, Collections.emptyList());
            if (roleGroup.isEmpty()) continue;

            int total = roleGroup.stream().mapToInt(p -> p.salary).sum();
            double avg = total / (double) roleGroup.size();
            MongoPerson min = roleGroup.stream().min(Comparator.comparingInt(p -> p.salary)).get();
            MongoPerson max = roleGroup.stream().max(Comparator.comparingInt(p -> p.salary)).get();

            stats.add(new Statistics(
                    String.valueOf(role),
                    roleGroup.size(),
                    avg,
                    new UniversalPerson(min.name, min.surname, min.salary, min.role),
                    new UniversalPerson(max.name, max.surname, max.salary, max.role)
            ));
        }

        // 📌 Sortowanie – "all" pierwsze
        stats.sort((a, b) -> {
            if ("all".equals(a.role)) return -1;
            if ("all".equals(b.role)) return 1;
            return Integer.compare(Integer.parseInt(a.role), Integer.parseInt(b.role));
        });

        return stats;
    }

}
