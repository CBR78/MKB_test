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

import com.mcb.creditfactory.model.CollateralType;
import com.mcb.creditfactory.service.CollateralTypeService;

@RestController
@RequestMapping("collateral_type")
public class CollateralTypeController {
    private static final String CUSTOM_HEADER_NAME = "X-Query-Result";
    private HttpHeaders headers = new HttpHeaders();
    @Autowired
    private CollateralTypeService collateralTypeService;

    @GetMapping
    public ResponseEntity<List<CollateralType>> getAll() {
        List<CollateralType> collateralTypes = collateralTypeService.getAll();
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME,
                "All objects CollateralType found. Number of objects " + collateralTypes.size());
        return new ResponseEntity<>(collateralTypes, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CollateralType> add(@RequestBody CollateralType collateralType) {
        CollateralType createdCollateralType = collateralTypeService.create(collateralType);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME,
                "Created CollateralType object with id " + createdCollateralType.getId());
        return new ResponseEntity<>(createdCollateralType, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CollateralType> update(@RequestBody CollateralType collateralType) {
        CollateralType updatedCollateralType = collateralTypeService.update(collateralType);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME,
                "Updated CollateralType object with id " + updatedCollateralType.getId());
        return new ResponseEntity<>(updatedCollateralType, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CollateralType> delete(@PathVariable Integer id) {
        collateralTypeService.delete(collateralTypeService.getById(id));
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Deleted CollateralType object with id " + id);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
