package za.bbd.algo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import za.bbd.algo.model.DataRequest;
import za.bbd.algo.model.DataResponse;
import za.bbd.algo.service.SortingService;


@RestController
public class DataController {

    SortingService service;
    @PostMapping("sort")
    public ResponseEntity<DataResponse> sortData(@RequestBody DataRequest dataRequest) {
        DataResponse response = service.sortWithAlgorithm(dataRequest);
        System.out.println(dataRequest);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    //@Autowired
    public DataController(SortingService service){
        this.service = service;
    }
}
