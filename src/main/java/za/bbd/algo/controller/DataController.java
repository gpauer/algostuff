package za.bbd.algo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import za.bbd.algo.model.DataRequest;
import za.bbd.algo.model.DataResponse;


@RestController
public class DataController {
    @PostMapping("sort")
    public ResponseEntity<DataResponse> sortData(@RequestBody DataRequest dataRequest) {
        System.out.println(dataRequest);
        return ResponseEntity.ok().build();
    }
}
