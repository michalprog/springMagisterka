package magisterka.spring.utils;
import magisterka.spring.models.PostPerson;

import java.util.List;
import java.util.Random;
public class PostPersonUtils {
    private static final Random random = new Random();

    private static final String[] names = {"Adam", "Ewa", "Kamil", "Zosia", "Marek", "Julia"};
    private static final String[] surnames = {"Krawczyk", "Mazur", "Baran", "Pawlak", "Król", "Sikora"};

    private static final String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
            + "Vestibulum et ligula in nunc bibendum fringilla a eu lectus.";

    public static List<PostPerson> transformPersons(List<PostPerson> persons) {
        return persons.stream().map(person -> {
            String newName = names[random.nextInt(names.length)];
            String newSurname = surnames[random.nextInt(surnames.length)];
            int newSalary = person.salary + random.nextInt(1000) + 500;
            String updatedDescription = (person.description != null ? person.description : "")
                    + "\n\n" + loremIpsum;

            // Tworzymy nowy obiekt (albo można nadpisywać istniejący)
            PostPerson updated = new PostPerson();
            updated.id = person.id; // zachowujemy ID
            updated.name = newName;
            updated.surname = newSurname;
            updated.salary = newSalary;
            updated.description = updatedDescription;
            updated.role = person.role;

            return updated;
        }).collect(Collectors.toList());
    }
}
