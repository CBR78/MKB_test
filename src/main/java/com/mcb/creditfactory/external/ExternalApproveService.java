package com.mcb.creditfactory.external;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.mcb.creditfactory.model.Assess;
import com.mcb.creditfactory.model.Collateral;

@Service
public class ExternalApproveService {
    public boolean approve(Collateral collateral, Assess assess) {

        Short year = collateral.getYearOfIssue();
        Short minYear = collateral.getCollateralType().getMinYearOfIssue();
        if (year < minYear) {
            return false;
        }

        LocalDate date = assess.getAssessedDate();
        LocalDate minDate = collateral.getCollateralType().getMinAssessedDate();
        if (date.isBefore(minDate)) {
            return false;
        }

        BigDecimal value = assess.getAssessedValue();
        BigDecimal minValue = collateral.getCollateralType().getMinAssessedValue();
        if (value.compareTo(minValue) < 0) {
            return false;
        }
        
        return true;
    }

    // В коде был комментарий "Для автомобилей дата оценки не используется, поэтому
    // всегда берем текущую" - согласовать и добавить в CollateralType
    // boolean-маркеры необходимости видов проверок, реализовать из использование в
    // классе проверки
}
