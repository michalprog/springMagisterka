package magisterka.spring.utils;

import magisterka.spring.repo.jpa.PostPerson;
import magisterka.spring.models.Statistics;
import magisterka.spring.models.UniversalPerson;
import magisterka.spring.repo.PostPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostPersonUtils {

    @Autowired
    private static PostPersonRepository repository;

    private static final Random random = new Random();
    private static final String[] names = {"Adam", "Ewa", "Kamil", "Zosia", "Marek", "Julia"};
    private static final String[] surnames = {"Krawczyk", "Mazur", "Baran", "Pawlak", "Król", "Sikora"};

    private static final String loremIpsum = """
Lorem ipsum dolor sit amet, consectetur adipiscing elit...
""";

    public PostPersonUtils(PostPersonRepository repository) {
        PostPersonUtils.repository = repository;
    }

    public static List<PostPerson> getAll() {
        return repository.findAll();
    }



    public static List<PostPerson> createPersons(List<PostPerson> persons) {
        return repository.saveAll(persons);
    }


    public static List<PostPerson> transformPersons(List<PostPerson> persons) {
        return persons.stream().map(person -> {
            String newName = names[random.nextInt(names.length)];
            String newSurname = surnames[random.nextInt(surnames.length)];
            int newSalary = person.salary + random.nextInt(1000) + 500;
            String updatedDescription = (person.description != null ? person.description : "")
                    + "\n\n" + loremIpsum;

            PostPerson updated = new PostPerson();
            updated.id = person.id;
            updated.name = newName;
            updated.surname = newSurname;
            updated.salary = newSalary;
            updated.description = updatedDescription;
            updated.role = person.role;

            return updated;
        }).collect(Collectors.toList());
    }

    public static List<Statistics> personStatistics() {
        List<PostPerson> persons = repository.findAll();
        if (persons.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Integer, List<PostPerson>> groupedByRole = persons.stream()
                .collect(Collectors.groupingBy(p -> p.role));

        List<Statistics> stats = new ArrayList<>();

        // all
        int totalAll = persons.stream().mapToInt(p -> p.salary).sum();
        double avgAll = (double) totalAll / persons.size();
        PostPerson minAll = persons.stream().min(Comparator.comparingInt(p -> p.salary)).orElseThrow();
        PostPerson maxAll = persons.stream().max(Comparator.comparingInt(p -> p.salary)).orElseThrow();

        stats.add(new Statistics(
                "all",
                persons.size(),
                avgAll,
                new UniversalPerson(minAll.name, minAll.surname, minAll.salary, minAll.role),
                new UniversalPerson(maxAll.name, maxAll.surname, maxAll.salary, maxAll.role)
        ));

        // per role
        for (int role = 0; role <= 4; role++) {
            List<PostPerson> roleGroup = groupedByRole.getOrDefault(role, Collections.emptyList());
            if (roleGroup.isEmpty()) continue;

            int total = roleGroup.stream().mapToInt(p -> p.salary).sum();
            double avg = (double) total / roleGroup.size();
            PostPerson min = roleGroup.stream().min(Comparator.comparingInt(p -> p.salary)).orElseThrow();
            PostPerson max = roleGroup.stream().max(Comparator.comparingInt(p -> p.salary)).orElseThrow();

            stats.add(new Statistics(
                    String.valueOf(role),
                    roleGroup.size(),
                    avg,
                    new UniversalPerson(min.name, min.surname, min.salary, min.role),
                    new UniversalPerson(max.name, max.surname, max.salary, max.role)
            ));
        }

        stats.sort((a, b) -> {
            if ("all".equals(a.role)) return -1;
            if ("all".equals(b.role)) return 1;
            return Integer.compare(Integer.parseInt(a.role), Integer.parseInt(b.role));
        });

        return stats;
    }
}
