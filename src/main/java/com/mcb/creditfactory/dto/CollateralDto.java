package com.mcb.creditfactory.dto;

import com.mcb.creditfactory.model.CollateralType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollateralDto {
    private Long id;
    private CollateralType collateralType;
    private Short yearOfIssue;
}
