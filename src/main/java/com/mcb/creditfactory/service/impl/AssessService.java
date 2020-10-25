package com.mcb.creditfactory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.creditfactory.model.Assess;
import com.mcb.creditfactory.repository.AssessRepository;
import com.mcb.creditfactory.service.BaseService;

@Service
public class AssessService implements BaseService<Assess> {
    private AssessRepository assessRepository;

    @Autowired
    public AssessService(AssessRepository assessRepository) {
        this.assessRepository = assessRepository;
    }

    public Assess create(Assess assess) {
        return assessRepository.save(assess);
    }

    public List<Assess> getAll() {
        return assessRepository.findAll();
    }

    public Assess getById(Long id) {
        return assessRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return assessRepository.existsById(id);
    }

    public Assess getLastAssess(Long collateralId) {
        // LocalDate maxDate = assessRepository.maxDate(collateralId);
        // Assess assess = assessRepository.getAssess(collateralId, maxDate);
        return assessRepository.getLastAssess(collateralId);
    }
}
