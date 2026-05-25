package magisterka.spring.controllers;
import magisterka.spring.repo.jpa.PostPerson;
import magisterka.spring.utils.PostPersonUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noDatabase")
public class NoDatabaseController {
    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }

    @PutMapping("/transformPersons")
    public List<PostPerson> transformPersons(@RequestBody List<PostPerson> persons) {
        return PostPersonUtils.transformPersons(persons);
    }
}