package com.mcb.creditfactory.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastAssessDTO {
    private Long id;
    private LocalDate assessedDate;
    private BigDecimal assessedValue;
}
