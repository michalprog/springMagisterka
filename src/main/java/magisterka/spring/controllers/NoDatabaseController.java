package magisterka.spring.controllers;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/noDatabase")
public class NoDatabaseController {
    @GetMapping("/heatlh")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/transformPersons")
    public void transformPersons()
    {

    }

}
