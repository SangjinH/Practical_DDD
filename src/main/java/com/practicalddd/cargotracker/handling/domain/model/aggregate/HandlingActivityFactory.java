package com.practicalddd.cargotracker.handling.domain.model.aggregate;

import com.practicalddd.cargotracker.booking.domain.model.aggregates.BookingId;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.Serializable;
import java.sql.Date;

@ApplicationScope
public class HandlingActivityFactory implements Serializable {

    public HandlingActivity createHandlingActivity(
            Date registrationTime,
            Date completionTime,
            BookingId bookingId,
            VoyageNumber voyageNumber,
            UnLocde unLocde,
            HandlingActivity.Type type) {

    }
}
