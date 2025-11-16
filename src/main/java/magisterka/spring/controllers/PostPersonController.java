package magisterka.spring.controllers;
import magisterka.spring.repo.jpa.PostPerson;
import magisterka.spring.models.Statistics;
import magisterka.spring.utils.PostPersonUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postPerson")
public class PostPersonController {

    public PostPersonController() {
    }

    @GetMapping("/getAll")
    public List<PostPerson> getALL() {
        return PostPersonUtils.getAll();
    }

    @PostMapping("/createPersons")
    public List<PostPerson> createPersons(@RequestBody List<PostPerson> persons ) {
        return PostPersonUtils.createPersons(persons);
    }

    @PutMapping("/personStatistics")
    public List<Statistics> personStatistics() {
        return PostPersonUtils.personStatistics();
    }


}
