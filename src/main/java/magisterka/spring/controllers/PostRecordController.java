package magisterka.spring.controllers;
import magisterka.spring.models.LimitRequest;
import magisterka.spring.repo.jpa.PostRecord;
import magisterka.spring.utils.PostRecordUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postRecord")
public class PostRecordController {

    @GetMapping("/getAll")
    public List<PostRecord> getALL(){
        return PostRecordUtils.getALL();
    }
    @GetMapping("/getRecords")
    public List<PostRecord> getRecords(@RequestBody LimitRequest request) {
        return PostRecordUtils.getRecords(request.limit);
    }
    @PostMapping("/createRecords")
    public List<PostRecord> createRecords(@RequestBody List<PostRecord> records){
        return PostRecordUtils.createRecords(records);
    }
    @PutMapping("/updateRecords")
    public List<PostRecord> updateRecords(@RequestBody List<PostRecord> records){
        return PostRecordUtils.updateRecords(records);
    }
    @DeleteMapping("/deleteRecords")
    public int deleteRecords(@RequestBody LimitRequest request) {
        return PostRecordUtils.deleteRecords(request.limit);
    }

}
