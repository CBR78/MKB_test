package com.mcb.creditfactory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.creditfactory.model.CollateralType;
import com.mcb.creditfactory.repository.CollateralTypeRepository;
import com.mcb.creditfactory.service.BaseService;

@Service
public class CollateralTypeService implements BaseService<CollateralType> {
    private CollateralTypeRepository collateralTypeRepository;

    @Autowired
    public CollateralTypeService(CollateralTypeRepository collateralTypeRepository) {
        this.collateralTypeRepository = collateralTypeRepository;
    }

    public CollateralType create(CollateralType collateralType) {
        return collateralTypeRepository.save(collateralType);
    }

    public List<CollateralType> getAll() {
        return collateralTypeRepository.findAll();
    }

    public CollateralType getById(long id) {
        return collateralTypeRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return collateralTypeRepository.existsById(id);
    }
}
