package com.oleg.warehouse.controller;

import com.oleg.warehouse.dto.WorkerDTO;
import com.oleg.warehouse.entity.WorkerEntity;
import com.oleg.warehouse.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/workers")
public class WorkerController {

    public static final String STORE = "";
    public static final String SHOW = "/{id}";
    public static final String UPDATE = "/{id}";
    public static final String DELETE = "/{id}";
    public static final String FETCH_BY_CONTRACTOR = "";

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping(STORE)
    public ResponseEntity<WorkerDTO> store(@RequestBody WorkerEntity workerEntity) {
        return ResponseEntity.ok(workerService.store(workerEntity));
    }


}
