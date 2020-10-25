package com.mcb.creditfactory.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastAssessDto {
    private Long id;

    @NotNull(message = "Request must include a LastAssessDto assessedDate.")
    private LocalDate assessedDate;

    @NotNull(message = "Request must include a LastAssessDto assessedValue.")
    private BigDecimal assessedValue;
}
