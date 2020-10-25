package com.mcb.creditfactory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcb.creditfactory.dto.CollateralAndAssessDto;
import com.mcb.creditfactory.service.impl.CollateralService;

@RestController
@RequestMapping("collateral")
@Validated
public class CollateralController {
    private static final String CUSTOM_HEADER_NAME = "X-Query-Result";
    private HttpHeaders headers = new HttpHeaders();
    @Autowired
    private CollateralService service;

    @GetMapping
    public ResponseEntity<List<CollateralAndAssessDto>> getAll() {
        List<CollateralAndAssessDto> collateral = service.getAll();
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME,
                "All objects Collateral found. Number of objects " + collateral.size());
        return new ResponseEntity<>(collateral, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CollateralAndAssessDto> add(@Validated @RequestBody CollateralAndAssessDto dto) {
        CollateralAndAssessDto createdDto = service.create(dto);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Created Collateral object with id " + createdDto.getId());
        return createdDto != null ? new ResponseEntity<>(createdDto, headers, HttpStatus.CREATED)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<CollateralAndAssessDto> getById(@PathVariable Long id) {
        CollateralAndAssessDto collateral = service.getById(id);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Found Collateral object with id " + collateral.getId());
        return collateral != null ? new ResponseEntity<>(collateral, headers, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }
}
