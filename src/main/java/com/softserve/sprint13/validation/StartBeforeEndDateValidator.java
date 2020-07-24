package com.softserve.sprint13.validation;

import com.softserve.sprint13.entity.Sprint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class StartBeforeEndDateValidator implements ConstraintValidator<StartBeforeEndDateValidation, Sprint> {

    @Override
    public void initialize(StartBeforeEndDateValidation annotation) {
    }

    @Override
    public boolean isValid(Sprint bean, ConstraintValidatorContext context) {
        final Date startDate = bean.getStartDate();
        final Date endDate = bean.getFinishDate();

        return !startDate.after(endDate);
    }
}
