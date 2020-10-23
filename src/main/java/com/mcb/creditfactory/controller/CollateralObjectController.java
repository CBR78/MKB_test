package com.mcb.creditfactory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mcb.creditfactory.dto.CollateralDto;
import com.mcb.creditfactory.service.CollateralService;

@RestController
public class CollateralObjectController {
    @Autowired
    private CollateralService service;

    @PostMapping("/collateral/save")
    public HttpEntity<Long> save(@RequestBody CollateralDto dto) {
        Long id = service.saveCollateral(dto);
        return id != null ? ResponseEntity.ok(id) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/collateral/info")
    public HttpEntity<CollateralDto> getInfo(@RequestBody CollateralDto dto) {
        CollateralDto info = service.getInfo(dto);
        return info != null ? ResponseEntity.ok(info) : ResponseEntity.notFound().build();
    }
}
