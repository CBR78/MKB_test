package com.mcb.creditfactory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollateralAndAssessDto {
    private Long id;
    private Long collateralTypeId;
    private Short yearOfIssue;
    private LastAssessDTO lastAssessDTO;
}
