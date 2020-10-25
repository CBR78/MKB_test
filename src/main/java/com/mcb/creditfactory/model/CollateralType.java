package com.mcb.creditfactory.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COLLATERAL_TYPE")
public class CollateralType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name")
    @NotNull(message = "Request must include a CollateralType typeName.")
    private String typeName;

    @Column(name = "min_year_of_issue")
    @NotNull(message = "Request must include a CollateralType minYearOfIssue.")
    private Short minYearOfIssue;

    @Column(name = "min_assessed_date")
    @NotNull(message = "Request must include a CollateralType minAssessedDate.")
    private LocalDate minAssessedDate;

    @Column(name = "min_assessed_value")
    @NotNull(message = "Request must include a CollateralType minAssessedValue.")
    private BigDecimal minAssessedValue;
}
