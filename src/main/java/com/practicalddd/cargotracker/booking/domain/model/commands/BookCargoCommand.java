package com.practicalddd.cargotracker.booking.domain.model.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Book Cargo Command Class
 */
@NoArgsConstructor
@Getter @Setter
public class BookCargoCommand {

    private String bookingId;
    private int bookingAmount;
    private String originLocation;
    private String destLocation;
    private Date destArrivalDeadline;

    public BookCargoCommand(int bookingAmount,
                            String originLocation, String destLocation, Date destArrivalDeadline) {

        this.bookingAmount = bookingAmount;
        this.originLocation = originLocation;
        this.destLocation = destLocation;
        this.destArrivalDeadline = destArrivalDeadline;
    }
}
