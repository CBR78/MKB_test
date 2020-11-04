package com.mcb.creditfactory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.creditfactory.dto.CollateralAndAssessDto;
import com.mcb.creditfactory.dto.AssessDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Assess;
import com.mcb.creditfactory.model.Collateral;
import com.mcb.creditfactory.model.CollateralType;
import com.mcb.creditfactory.repository.CollateralRepository;
import com.mcb.creditfactory.service.BaseService;

@Service
public class CollateralService implements BaseService<Collateral> {
    private CollateralRepository collateralRepository;
    private CollateralTypeService collateralTypeService;
    private AssessService assessService;
    private ExternalApproveService approveService;

    @Autowired
    public CollateralService(CollateralTypeService collateralTypeService, AssessService assessService,
            ExternalApproveService approveService, CollateralRepository collateralRepository) {
        this.collateralRepository = collateralRepository;
        this.collateralTypeService = collateralTypeService;
        this.assessService = assessService;
        this.approveService = approveService;
    }

    public CollateralAndAssessDto create(CollateralAndAssessDto collateralDto) {
        Collateral collateral = collateralFromDto(collateralDto);
        Assess assess = assessFromDto(collateralDto);

        boolean approved = approveService.approve(collateral, assess);
        if (!approved) {
            return null;
        }
        collateralRepository.save(collateral);
        assess.setCollateral(collateral);
        assessService.create(assess);
        return toDto(collateral, assess);
    }

    public List<CollateralAndAssessDto> getAll() {
        List<Collateral> collaterals = collateralRepository.findAll();
        List<CollateralAndAssessDto> listDto = new ArrayList<>();

        for (Collateral collateral : collaterals) {
            Assess assess = assessService.getLastAssess(collateral.getId());
            CollateralAndAssessDto dto = toDto(collateral, assess);
            listDto.add(dto);
        }
        return listDto;
    }

    public CollateralAndAssessDto getById(Long collateralId) {
        Optional<Collateral> optCollateral = collateralRepository.findById(collateralId);

        if (optCollateral.isPresent()) {
            Collateral collateral = optCollateral.get();
            Assess assess = assessService.getLastAssess(collateralId);
            return toDto(collateral, assess);
        } else {
            return null;
        }
    }
    
    @Override
    public boolean existsById(Long id) {
        return collateralRepository.existsById(id);
    }

    private Collateral collateralFromDto(CollateralAndAssessDto collateralDto) {
        CollateralType collateralType = collateralTypeService
                .getById(collateralDto.getCollateralTypeId());
        return new Collateral(null, collateralType, collateralDto.getYearOfIssue());
    }

    private Assess assessFromDto(CollateralAndAssessDto collateralDto) {
        AssessDto lastAssessDTO = collateralDto.getLastAssessDTO();
        return new Assess(null, null, lastAssessDTO.getAssessedDate(), lastAssessDTO.getAssessedValue());
    }

    private CollateralAndAssessDto toDto(Collateral collateral, Assess assess) {
        AssessDto lastAssessDTO = new AssessDto(assess.getId(), assess.getAssessedDate(),
                assess.getAssessedValue());

        return new CollateralAndAssessDto(collateral.getId(), collateral.getCollateralType().getId(),
                collateral.getYearOfIssue(), lastAssessDTO);
    }
}
