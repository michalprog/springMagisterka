package magisterka.spring.utils;

import magisterka.spring.repo.jpa.PostPerson;
import magisterka.spring.models.Statistics;
import magisterka.spring.models.UniversalPerson;
import magisterka.spring.repo.PostPersonRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostPersonUtils {

    private final PostPersonRepository repository;

    private static final Random random = new Random();
    private static final String[] names = {"Adam", "Ewa", "Kamil", "Zosia", "Marek", "Julia"};
    private static final String[] surnames = {"Krawczyk", "Mazur", "Baran", "Pawlak", "Król", "Sikora"};

    private static final String loremIpsum = """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus urna lacus, aliquam vel quam eu, ultricies rutrum risus. Donec id sapien tempus, finibus lectus ac, pretium nisi. Sed lorem ligula, sagittis vitae turpis vitae, lobortis mattis nulla. Cras molestie ut ante id pulvinar. Curabitur auctor commodo sem, id tincidunt lorem eleifend ac. Proin pellentesque sapien libero, eu lobortis velit feugiat blandit. Ut molestie in velit nec tristique.

    Phasellus et libero metus. Pellentesque in accumsan eros. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Praesent vehicula turpis sed magna aliquam aliquam. Aenean nulla risus, pretium ultricies vulputate eget, varius at tellus. Nulla quis tincidunt quam. Nulla mauris nibh, semper aliquam nulla at, blandit cursus odio.

    Aliquam nisi dui, lacinia a orci a, tristique dapibus nisi. Vestibulum nec blandit dolor, ut scelerisque tellus. Mauris mollis enim ac tristique ornare. Nullam nec nisi a mauris consequat semper vitae egestas lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris id dui nec nisl facilisis rhoncus. Vestibulum pulvinar accumsan nunc, ut placerat felis laoreet vel. Integer fermentum lacinia leo sed sagittis.

    Fusce porta risus sit amet elit vulputate, eget suscipit ipsum luctus. Quisque elementum vel lectus placerat dapibus. Duis eu scelerisque felis, eu auctor velit. Donec interdum blandit tortor eu fermentum. Suspendisse maximus nunc lectus, a venenatis enim malesuada in. Sed imperdiet varius ex in lobortis. Mauris bibendum et arcu et tincidunt.

    Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam rutrum mollis libero sed mattis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Sed gravida aliquet pretium. Suspendisse in urna vehicula, mattis risus non, maximus risus. Integer accumsan tortor non tempus placerat. Morbi sed leo a arcu pellentesque sollicitudin quis sed ipsum. Mauris semper massa ac auctor dapibus. Vestibulum elementum pulvinar posuere.
    """;


    public PostPersonUtils(PostPersonRepository repository) {
        this.repository = repository;
    }

    public List<PostPerson> getAll() {
        return repository.findAll();
    }



    public List<PostPerson> createPersons(List<PostPerson> persons) {
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

    public List<Statistics> personStatistics() {
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
