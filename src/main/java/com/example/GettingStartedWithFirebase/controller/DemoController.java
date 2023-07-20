package com.example.GettingStartedWithFirebase.controller;

import com.example.GettingStartedWithFirebase.model.DemoModel;
import com.example.GettingStartedWithFirebase.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping("addDemo")
    public ResponseEntity<String> addDemo(@RequestBody String demoJson) {

        DemoModel demo = demoService.deserializeJsonToDemoModel(demoJson);

        try {
            return new ResponseEntity<>(demoService.addDemo(demo), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed to add the desired demo", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("displayAllDemos")
    public ResponseEntity<List<DemoModel>> displayAllDemos() {
        try {
            List<DemoModel> demos = demoService.getAllDemos();
            return new ResponseEntity<List<DemoModel>>(demos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("updateDemo")
    public ResponseEntity<String> updateDemo(@RequestBody String documentId) {
        try {
            demoService.updateDemo(documentId);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed to update the desired demo", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("deleteDemo")
    public ResponseEntity<String> deleteDemo(@RequestBody String documentId) {
        try {
            demoService.deleteDemo(documentId);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed to delete the desired demo", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("test")
    public ResponseEntity<String> testController() {
        return ResponseEntity.ok("Test Get Endpoint is working!");
    }
}
