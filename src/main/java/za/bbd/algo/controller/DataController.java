package za.bbd.algo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import za.bbd.algo.model.DataRequest;
import za.bbd.algo.model.DataResponse;


@RestController
public class DataController {
    @GetMapping("compute")
    public ResponseEntity<DataResponse> sortData(@RequestBody DataRequest dataRequest) {
        return ResponseEntity.ok().build();
    }
}
