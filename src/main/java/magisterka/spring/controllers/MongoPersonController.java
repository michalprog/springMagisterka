package magisterka.spring.controllers;

import magisterka.spring.models.MongoPerson;
import magisterka.spring.models.Statistics;
import magisterka.spring.utils.MongoPersonUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongoPerson")
public class MongoPersonController {

    private final MongoPersonUtils utils;

    public MongoPersonController(MongoPersonUtils utils) {
        this.utils = utils;
    }

    @GetMapping("/getAll")
    public List<MongoPerson> getALL() {
        return utils.getAll();
    }

    @PostMapping("/createPersons")
    public List<MongoPerson> createPersons(@RequestBody List<MongoPerson> persons) {
        return utils.createPersons(persons);
    }

    @PutMapping("/personStatistics")
    public List<Statistics> personStatistics() {
        return utils.personStatistics();
    }
}
