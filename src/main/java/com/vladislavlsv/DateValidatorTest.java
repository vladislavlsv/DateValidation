package com.vladislavlsv;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static com.vladislavlsv.DateValidator.*;

@RunWith(MockitoJUnitRunner.class)
public class DateValidatorTest {

    LocalDate now = LocalDate.now();

    @Test
    public void validateFromToDates_PastDates_ShouldBeOk() {
        LocalDate dateFrom = LocalDate.now().minusDays(1);
        LocalDate dateTo = LocalDate.now().plusDays(1);
        validateFromToDates(canBePast(dateFrom), inclusive(canBePast(dateTo)));
    }

    @Test(expected = DateValidationException.class)
    public void validateFromToDates_FromLargerThanTo_ShouldBeException() {
        LocalDate dateFrom = now.minusDays(1);
        LocalDate dateTo = now.plusDays(1);
        validateFromToDates(canBePast(dateTo), inclusive(canBePast(dateFrom)));
    }

    @Test
    public void validateFromToDates_InclusiveWithEqualDates_ShouldBeOk() {
        LocalDate date = now;
        validateFromToDates(canBePast(date), inclusive(canBePast(date)));
    }

    @Test(expected = DateValidationException.class)
    public void validateFromToDates_ExclusiveWithEqualDates_ShouldBeException() {
        LocalDate date = now;
        validateFromToDates(canBePast(date), exclusive(canBePast(date)));
    }

    @Test(expected = DateValidationException.class)
    public void validateFromToDates_DatesShouldBeInFuture_ShouldBeException() {
        LocalDate dateFrom = now.minusDays(1);
        LocalDate dateTo = now.plusDays(1);
        validateFromToDates(mustBeFuture(dateFrom), inclusive(mustBeFuture(dateTo)));
    }

    @Test
    public void validateFromToDates_FutureDates_ShouldBeOk() {
        LocalDate dateFrom = now.plusDays(1);
        LocalDate dateTo = now.plusDays(10);
        validateFromToDates(mustBeFuture(dateFrom), inclusive(mustBeFuture(dateTo)));
    }
}
