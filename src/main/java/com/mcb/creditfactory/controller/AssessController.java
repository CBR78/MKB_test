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

import com.mcb.creditfactory.model.Assess;
import com.mcb.creditfactory.service.AssessService;

@RestController
@RequestMapping("assess")
public class AssessController {
    private static final String CUSTOM_HEADER_NAME = "X-Query-Result";
    private HttpHeaders headers = new HttpHeaders();
    @Autowired
    private AssessService assessService;

    @GetMapping
    public ResponseEntity<List<Assess>> getAll() {
        List<Assess> assess = assessService.getAll();
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "All objects Assess found. Number of objects " + assess.size());
        return new ResponseEntity<>(assess, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Assess> add(@RequestBody Assess assess) {
        Assess createdAssess = assessService.create(assess);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Created Assess object with id " + createdAssess.getId());
        return new ResponseEntity<>(createdAssess, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Assess> update(@RequestBody Assess assess) {
        Assess updatedAssess = assessService.update(assess);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Updated Assess object with id " + updatedAssess.getId());
        return new ResponseEntity<>(updatedAssess, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Assess> delete(@PathVariable Integer id) {
        assessService.delete(assessService.getById(id));
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Deleted Assess object with id " + id);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
