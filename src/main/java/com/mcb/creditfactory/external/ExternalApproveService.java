package com.mcb.creditfactory.external;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.mcb.creditfactory.model.Collateral;

@Service
public class ExternalApproveService {
    public boolean approve(Collateral collateral) {
        Short minYear = collateral.getCollateralType().getMinYearOfIssue();
        LocalDate minDate = collateral.getCollateralType().getMinAssessedDate();
        BigDecimal minValue = collateral.getCollateralType().getMinAssessedValue();

        Short year = collateral.getYearOfIssue();
        LocalDate date = collateral.getLastAssess().getAssessedDate();
        BigDecimal value = collateral.getLastAssess().getAssessedValue();

        if (year == null || date == null || value == null || minYear == null || minDate == null
                || minValue == null) {
            return false;
        }

        if (year < minYear) {
            return false;
        }
        if (date.isBefore(minDate)) {
            return false;
        }
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
