package magisterka.spring.controllers;
import magisterka.spring.models.PostPerson;
import magisterka.spring.models.Statistics;
import magisterka.spring.utils.PostPersonUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postPerson")
public class PostPersonController {
    private final PostPersonUtils utils;

    public PostPersonController(PostPersonUtils utils) {
        this.utils = utils;
    }

    @GetMapping("/getAll")
    public List<PostPerson> getALL() {
        return utils.getAll();
    }

    @PostMapping("/createPersons")
    public List<PostPerson> createPersons(@RequestBody List<PostPerson> persons ) {
        return utils.createPersons(persons);
    }

    @PutMapping("/personStatistics")
    public List<Statistics> personStatistics() {
        return utils.personStatistics();
    }


}
