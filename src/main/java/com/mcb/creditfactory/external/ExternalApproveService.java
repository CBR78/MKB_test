package com.mcb.creditfactory.external;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.mcb.creditfactory.dto.CollateralDto;

@Service
public class ExternalApproveService {
    public int approve(CollateralDto dto) {
        Short year = dto.getYearOfIssue();
        LocalDate date = LocalDate.now(); // исправить - извлекается из Last_ASSESS
        
        // В коде был комментарий "Для автомобилей дата оценки не используется, поэтому
        // всегда берем текущую" - согласовать и добавить в CollateralType
        // boolean-маркеры необходимости видов проверок, реализовать из использование в
        // классе проверки
        
        BigDecimal value = null; // исправить - извлекается из Last_Last ASSESS

        Short minYear = dto.getCollateralType().getMinYearOfIssue();
        LocalDate minDate = dto.getCollateralType().getMinAssessedDate();
        BigDecimal minValue = dto.getCollateralType().getMinAssessedValue();

        if (year == null || date == null || value == null || minYear == null || minDate == null
                || minValue == null) {
            return -1;
        }

        if (year < minYear) {
            return -10;
        }
        if (date.isBefore(minDate)) {
            return -11;
        }
        if (value.compareTo(minValue) < 0) {
            return -12;
        }

        return 0;
    }
}
