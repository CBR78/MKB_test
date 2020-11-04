package com.mcb.creditfactory.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.mcb.creditfactory.validation.IdExistsInDb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollateralAndAssessDto {
    private Long id;

    @NotNull(message = "Request must include a CollateralAndAssessDto collateralTypeId.")
    @IdExistsInDb(typeObject = "CollateralType", message = "This CollateralType id is not in the database.")
    private Long collateralTypeId;

    @NotNull(message = "Request must include a CollateralAndAssessDto yearOfIssue.")
    private Short yearOfIssue;

    @NotNull(message = "Request must include a CollateralAndAssessDto lastAssessDTO.")
    @Valid
    private AssessDto lastAssessDTO;
}
