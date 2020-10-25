package com.mcb.creditfactory.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.creditfactory.dto.LastAssessDTO;
import com.mcb.creditfactory.dto.CollateralAndAssessDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Assess;
import com.mcb.creditfactory.model.Collateral;
import com.mcb.creditfactory.model.CollateralType;
import com.mcb.creditfactory.repository.CollateralRepository;

@Service
public class CollateralService {
    @Autowired
    private CollateralTypeService collateralTypeService;

    @Autowired
    private AssessService assessService;

    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private CollateralRepository collateralRepository;

    @Autowired
    private EntityManager entityManager;

//    public Long saveCollateral(CollateralDto dto) {
//        boolean approved = approveService.approve(dto);
//        if (!approved) {
//            return null;
//        }
//        return Optional.of(dto).map(optDto -> fromNewDto(optDto))
//                .map(collateral -> collateralRepository.save(collateral))
//                .map(collateral -> collateral.getId()).orElse(null);
//    }

//    public CollateralDto getInfo(CollateralDto dto) {
//        return Optional.of(dto).map(optDto -> fromNewDto(optDto)).map(collateral -> collateral.getId())
//                .flatMap(id -> collateralRepository.findById(id)).map(collateral -> toDTO(collateral))
//                .orElse(null);
//    }

//    private Collateral fromDto(CollateralDto dto) {
//        return new Collateral(dto.getId(), dto.getCollateralType(), dto.getYearOfIssue());
//    }

    private Collateral fromNewDto(CollateralAndAssessDto newCollateralDto) {

        Long collateralTypeId = newCollateralDto.getCollateralTypeId();
        CollateralType collateralType = collateralTypeService.getById(collateralTypeId);

        Short yearOfIssue = newCollateralDto.getYearOfIssue();

        LastAssessDTO lastAssessDTO = newCollateralDto.getLastAssessDTO();
        Assess lastAssess = new Assess(null, null, lastAssessDTO.getAssessedDate(),
                lastAssessDTO.getAssessedValue());

        return new Collateral(null, collateralType, yearOfIssue, lastAssess);
    }

//    private NewCollateralAndAssessDto toDTO(NewCollateralAndAssessDto newCollateralDto, Collateral savedCollateral, ) {
//        LastAssessDTO lastAssessDTO = newCollateralDto.getLastAssessDTO();
//        lastAssessDTO.se
//        
//        
//        return newCollateralDto.setLastAssessDTO(lastAssessDTO);
//    }

    // =======================================

    public CollateralAndAssessDto create(CollateralAndAssessDto newCollateralDto) {
        Collateral collateral = fromNewDto(newCollateralDto);

        boolean approved = approveService.approve(collateral);
        if (!approved) {
            return null;
        }

        Collateral savedCollateral = collateralRepository.save(collateral);

        Assess lastAssess = savedCollateral.getLastAssess();
        lastAssess.setCollateral(savedCollateral);
        Assess savedAssess = assessService.create(lastAssess);

        newCollateralDto.setId(savedCollateral.getId());
        newCollateralDto.getLastAssessDTO().setId(savedAssess.getId());
        return newCollateralDto;
    }

    public Collateral update(Collateral collateral) {
        return collateralRepository.save(collateral);
    }

    public void delete(Collateral collateral) {
        collateralRepository.delete(collateral);
    }

    public List<Collateral> getAll() {
        entityManager.clear();
        return collateralRepository.findAll();
    }

    public Collateral getById(long id) {
        return collateralRepository.findById(id).get();
    }

    public boolean existsById(long id) {
        return collateralRepository.existsById(id);
    }
}
