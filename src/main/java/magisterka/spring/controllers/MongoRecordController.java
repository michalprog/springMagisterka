package magisterka.spring.controllers;

import magisterka.spring.models.LimitRequest;
import magisterka.spring.repo.mongo.MongoRecord;
import magisterka.spring.utils.MongoRecordUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongoRecord")
public class MongoRecordController {

    private final MongoRecordUtils utils;

    public MongoRecordController(MongoRecordUtils utils) {
        this.utils = utils;
    }

    @GetMapping("/getAll")
    public List<MongoRecord> getALL() {
        return utils.getALL();
    }

    @GetMapping("/getRecords")
    public List<MongoRecord> getRecords(@RequestParam int limit) {
        return utils.getRecords(limit);
    }

    @PostMapping("/createRecords")
    public List<MongoRecord> createRecords(@RequestBody List<MongoRecord> records) {
        return utils.createRecords(records);
    }

    @PutMapping("/updateRecords")
    public List<MongoRecord> updateRecords(@RequestBody List<MongoRecord> records) {
        return utils.updateRecords(records);
    }

    @DeleteMapping("/deleteRecords")
    public long deleteRecords(@RequestBody LimitRequest request) {
        return utils.deleteRecords(request.limit);
    }
}