package com.vladislavlsv;

public class DateConditionExtended {
    private DateCondition dateCondition;
    private boolean isExclusive;

    public DateConditionExtended(DateCondition dateCondition, boolean isExclusive) {
        this.dateCondition = dateCondition;
        this.isExclusive = isExclusive;
    }

    public DateCondition getDateCondition(){
        return dateCondition;
    }

    public boolean isExclusive() {
        return isExclusive;
    }
}
