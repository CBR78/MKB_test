package com.mcb.creditfactory.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.creditfactory.model.CollateralType;
import com.mcb.creditfactory.repository.CollateralTypeRepository;

@Service
public class CollateralTypeService {
    private CollateralTypeRepository collateralTypeRepository;
    private EntityManager entityManager;

    @Autowired
    public CollateralTypeService(CollateralTypeRepository collateralTypeRepository,
            EntityManager entityManager) {
        this.collateralTypeRepository = collateralTypeRepository;
        this.entityManager = entityManager;
    }

    public CollateralType create(CollateralType collateralType) {
        return collateralTypeRepository.save(collateralType);
    }

    public CollateralType update(CollateralType collateralType) {
        return collateralTypeRepository.save(collateralType);
    }

    public void delete(CollateralType collateralType) {
        collateralTypeRepository.delete(collateralType);
    }

    public List<CollateralType> getAll() {
        entityManager.clear();
        return collateralTypeRepository.findAll();
    }

    public CollateralType getById(long id) {
        return collateralTypeRepository.findById(id).get();
    }

    public boolean existsById(long id) {
        return collateralTypeRepository.existsById(id);
    }
}
