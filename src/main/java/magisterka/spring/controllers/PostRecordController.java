package magisterka.spring.controllers;
import magisterka.spring.models.LimitRequest;
import magisterka.spring.repo.jpa.PostRecord;
import magisterka.spring.utils.PostRecordUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postRecord")
public class PostRecordController {

    private final PostRecordUtils utils;

    public PostRecordController(PostRecordUtils utils) {
        this.utils = utils;
    }

    @GetMapping("/getAll")
    public List<PostRecord> getALL(){
        return utils.getALL();
    }
    @GetMapping("/getRecords")
    public List<PostRecord> getRecords(@RequestParam int limit) {
        return utils.getRecords(limit);
    }
    @PostMapping("/createRecords")
    public List<PostRecord> createRecords(@RequestBody List<PostRecord> records){
        return utils.createRecords(records);
    }
    @PutMapping("/updateRecords")
    public List<PostRecord> updateRecords(@RequestBody List<PostRecord> records){
        return utils.updateRecords(records);
    }
    @DeleteMapping("/deleteRecords")
    public int deleteRecords(@RequestBody LimitRequest request) {
        return utils.deleteRecords(request.limit);
    }

}