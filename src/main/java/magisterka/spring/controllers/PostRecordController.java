package magisterka.spring.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postRecord")
public class PostRecordController {
    @GetMapping("/getAll")
    public void getALL(){
    }
    @GetMapping("/getRecords")
    public void getRecords(){
    }
    @PostMapping("/createRecords")
    public void createRecords(){
    }
    @PutMapping("/updateRecords")
    public void updateRecords(){
    }
    @DeleteMapping("/deleteRecords")
    public void deleteRecords(){
    }

}
