package com.mcb.creditfactory.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.creditfactory.model.Assess;
import com.mcb.creditfactory.repository.AssessRepository;

@Service
public class AssessService {
    private AssessRepository assessRepository;
    private EntityManager entityManager;

    @Autowired
    public AssessService(AssessRepository assessRepository, EntityManager entityManager) {
        this.assessRepository = assessRepository;
        this.entityManager = entityManager;
    }

    public Assess create(Assess assess) {
        return assessRepository.save(assess);
    }

    public Assess update(Assess assess) {
        return assessRepository.save(assess);
    }

    public void delete(Assess assess) {
        assessRepository.delete(assess);
    }

    public List<Assess> getAll() {
        entityManager.clear();
        return assessRepository.findAll();
    }

    public Assess getById(long id) {
        return assessRepository.findById(id).get();
    }

    public boolean existsById(long id) {
        return assessRepository.existsById(id);
    }
}
