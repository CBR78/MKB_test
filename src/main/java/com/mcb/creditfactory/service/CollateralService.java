package com.mcb.creditfactory.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcb.creditfactory.dto.CollateralDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Collateral;
import com.mcb.creditfactory.repository.CollateralRepository;

@Service
public class CollateralService {
    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private CollateralRepository collateralRepository;
    
    public Long saveCollateral(CollateralDto dto) {
        boolean approved = approveService.approve(dto) == 0;
        if (!approved) {
            return null;
        }
        return Optional.of(dto)
                .map( optDto -> fromDto(optDto))
                .map( collateral -> collateralRepository.save(collateral))
                .map( collateral -> collateral.getId())
                .orElse(null);
    }

    public CollateralDto getInfo(CollateralDto dto) {
        return Optional.of(dto)
                .map( optDto -> fromDto(optDto))
                .map( collateral -> collateral.getId())
                .flatMap( id -> collateralRepository.findById(id))
                .map( collateral -> toDTO(collateral))
                .orElse(null);
    }
    
    private Collateral fromDto(CollateralDto dto) {
        return new Collateral(
                dto.getId(), 
                dto.getCollateralType(), 
                dto.getYearOfIssue());
    }

    private CollateralDto toDTO(Collateral collateral) {
        return new CollateralDto(
                collateral.getId(), 
                collateral.getCollateralType(),
                collateral.getYearOfIssue());
    }
}
