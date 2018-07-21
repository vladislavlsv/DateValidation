package com.vladislavlsv;

import java.time.LocalDate;

public class DateCondition {

private LocalDate localDate;
private boolean isMustBeFuture;

    public DateCondition(LocalDate localDate, boolean isMustBeFuture) {
        this.localDate = localDate;
        this.isMustBeFuture = isMustBeFuture;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public boolean isMustBeFuture() {
        return isMustBeFuture;
    }
}
