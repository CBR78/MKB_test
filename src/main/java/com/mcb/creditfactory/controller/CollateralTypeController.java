package com.mcb.creditfactory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcb.creditfactory.model.CollateralType;
import com.mcb.creditfactory.service.impl.CollateralTypeService;

@RestController
@RequestMapping("collateral_type")
@Validated
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
    public ResponseEntity<CollateralType> add(@Validated @RequestBody CollateralType collateralType) {
        CollateralType createdCollateralType = collateralTypeService.create(collateralType);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME,
                "Created CollateralType object with id " + createdCollateralType.getId());
        return new ResponseEntity<>(createdCollateralType, headers, HttpStatus.CREATED);
    }
}
