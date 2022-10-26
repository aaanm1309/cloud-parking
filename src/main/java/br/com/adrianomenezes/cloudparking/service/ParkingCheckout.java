package br.com.adrianomenezes.cloudparking.service;

import br.com.adrianomenezes.cloudparking.entity.Parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingCheckout {

    public static final int ONE_HOUR = 60;
    public static final int TWENTY_FOUR_HOUR = 24 * ONE_HOUR;
    public static final double ONE_HOUR_VALUE = 9.00;
    public static final double ADDITIONAL_PER_HOUR_VALUE = 2.00;
    public static final double DAY_VALUE = 50.00;

    public static Double getBill(Parking parking) {
        return getBill(parking.getEntryDate(), parking.getExitDate());
    }

    private static Double getBill(LocalDateTime entryDate, LocalDateTime exitDate) {
        long minutes = entryDate.until(exitDate, ChronoUnit.MINUTES);
        Double bill = 0.00;
        int days = (int) (minutes / TWENTY_FOUR_HOUR);
        if (days >=1) {
            bill += DAY_VALUE * (days);
            minutes -= (TWENTY_FOUR_HOUR * days);
        }

        int hours = (int) (minutes / ONE_HOUR);
        bill += ADDITIONAL_PER_HOUR_VALUE * hours  ;

        bill += ONE_HOUR_VALUE;

        return bill;
    }


}
