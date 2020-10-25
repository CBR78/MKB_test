package com.mcb.creditfactory.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mcb.creditfactory.model.Assess;
import com.mcb.creditfactory.model.Collateral;
import com.mcb.creditfactory.model.CollateralType;
import com.mcb.creditfactory.service.BaseService;
import com.mcb.creditfactory.service.impl.AssessService;
import com.mcb.creditfactory.service.impl.CollateralService;
import com.mcb.creditfactory.service.impl.CollateralTypeService;

public class IdExistsInDbValidator implements ConstraintValidator<IdExistsInDb, Long> {

    private BaseService<CollateralType> collateralTypeService;
    private BaseService<Collateral> collateralService;
    private BaseService<Assess> assessService;
    private BaseService<?> currentService;

    @Autowired
    private IdExistsInDbValidator(CollateralTypeService collateralTypeService,
            CollateralService collateralService, AssessService assessService) {
        this.collateralTypeService = collateralTypeService;
        this.collateralService = collateralService;
        this.assessService = assessService;
    }

    @Override
    public void initialize(IdExistsInDb exist) {
        String typeObject = exist.typeObject();

        switch (typeObject) {
        case "CollateralType":
            this.currentService = collateralTypeService;
            break;
        case ("Collateral"):
            this.currentService = collateralService;
            break;
        case ("Assess"):
            this.currentService = assessService;
            break;
        default:
            throw new IllegalArgumentException(
                    "typeObject parameter accepts only 1 of 3 values - CollateralType, Collateral, Assess");
        }
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        if (id == null) {
            return true;
        }

        return currentService.existsById(id);
    }
}
