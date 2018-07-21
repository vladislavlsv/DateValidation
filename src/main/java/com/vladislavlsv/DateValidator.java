package com.vladislavlsv;

import java.time.LocalDate;

public class DateValidator {

    static LocalDate now = LocalDate.now();

    public static void validateFromToDates(DateCondition dateConditionFrom, DateConditionExtended dateConditionTo)
            throws DateValidationException {
        LocalDate realDateFrom = dateConditionFrom.getLocalDate();
        LocalDate realDateTo = dateConditionTo.getDateCondition().getLocalDate();

        validateFirstStep(dateConditionFrom, realDateFrom);
        validateSecondStep(dateConditionTo, realDateTo);
        validateThirdStep(dateConditionTo, realDateFrom, realDateTo);
        validateFourthStep(realDateFrom, realDateTo);

    }

    public static void validateFirstStep(DateCondition dateConditionFrom, LocalDate realDateFrom) {
        if(dateConditionFrom.isMustBeFuture() && !realDateFrom.isAfter(now)) {
            throw new DateValidationException("DateFrom < current date. Must be in future");
        }
    }

    public static void validateSecondStep(DateConditionExtended dateConditionTo, LocalDate realDateTo) {
        if(dateConditionTo.getDateCondition().isMustBeFuture() && !realDateTo.isAfter(now)) {
            throw new DateValidationException("DateTo < current date. Must be in future");
        }
    }

    public static void validateThirdStep(DateConditionExtended dateConditionTo, LocalDate realDateFrom,
                                         LocalDate realDateTo) {
        if(dateConditionTo.isExclusive() && realDateFrom.equals(realDateTo)) {
            throw new DateValidationException("Dates can't be the same");
        }
    }

    public static void validateFourthStep(LocalDate realDateFrom, LocalDate realDateTo) {
        if(realDateFrom.isAfter(realDateTo)){
            throw new DateValidationException("DateTo can't be less than DateFrom");
        }
    }

    public static DateCondition canBePast(LocalDate localDate) {
        return new DateCondition(localDate, false);
    }

    public static DateCondition mustBeFuture(LocalDate localDate) {
        return new DateCondition(localDate, true);
    }

    public static DateConditionExtended exclusive(DateCondition dateCondition) {
        return new DateConditionExtended(dateCondition, true);
    }

    public static DateConditionExtended inclusive(DateCondition dateCondition) {
        return new DateConditionExtended(dateCondition, false);
    }
}
