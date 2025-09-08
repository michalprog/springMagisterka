package magisterka.spring.controllers;
import magisterka.spring.models.PostPerson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postPerson")
public class PostPersonController {
    @GetMapping("/getAll")
    public List<PostPerson> getALL(){
    }
    @PostMapping("/createPersons")
    public List<PostPerson> createPersons(){

    }
    @PutMapping("/personStatistics")
    public void personStatistics(){
    }


}
