package com.mcb.creditfactory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcb.creditfactory.dto.CollateralAndAssessDto;
import com.mcb.creditfactory.model.Collateral;
import com.mcb.creditfactory.service.CollateralService;

@RestController
@RequestMapping("collateral")
public class CollateralController {
    private static final String CUSTOM_HEADER_NAME = "X-Query-Result";
    private HttpHeaders headers = new HttpHeaders();
    @Autowired
    private CollateralService service;

//    @PostMapping("/save")
//    public HttpEntity<Long> save(@RequestBody CollateralDto dto) {
//        Long id = service.saveCollateral(dto);
//        return id != null ? ResponseEntity.ok(id) : ResponseEntity.badRequest().build();
//    }
//
//    @PostMapping("/info")
//    public HttpEntity<CollateralDto> getInfo(@RequestBody CollateralDto dto) {
//        CollateralDto info = service.getInfo(dto);
//        return info != null ? ResponseEntity.ok(info) : ResponseEntity.notFound().build();
//    }

    // -------
    @GetMapping
    public ResponseEntity<List<Collateral>> getAll() {
        List<Collateral> collateral = service.getAll();
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME,
                "All objects Collateral found. Number of objects " + collateral.size());
        return new ResponseEntity<>(collateral, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CollateralAndAssessDto> add(@RequestBody CollateralAndAssessDto dto) {
        CollateralAndAssessDto createdDto = service.create(dto);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Created Collateral object with id " + createdDto.getId());
        return createdDto != null ? new ResponseEntity<>(createdDto, headers, HttpStatus.CREATED)
                : ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Collateral> update(@RequestBody Collateral collateral) {
        Collateral updatedCollateral = service.update(collateral);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME,
                "Updated Collateral object with id " + updatedCollateral.getId());
        return new ResponseEntity<>(updatedCollateral, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Collateral> delete(@PathVariable Integer id) {
        service.delete(service.getById(id));
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Deleted Collateral object with id " + id);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
