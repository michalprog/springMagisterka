package magisterka.spring.utils;
import magisterka.spring.models.PostPerson;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PostPersonUtils {
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

    public void getALL(){
    }

    public void createPersons(){

    }

    public void personStatistics(){
    }
}
