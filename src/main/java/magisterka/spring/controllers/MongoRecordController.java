package magisterka.spring.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mongoRecord")
public class MongoRecordController {

    @GetMapping("/getAll")
    public void getALL(){

    }
    @PostMapping("/createPersons")
    public void createPersons(){

    }
    @PutMapping("/personStatistics")
    public void personStatistics(){
    }

}
