package com.practicalddd.cargotracker.booking.domain.model.aggregate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cargo implements Serializable {

    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Embedded
    private BookingId bookingId;


    @ManyToOne(fetch = FetchType.LAZY)
    private Location origin;

    @Embedded
    private CargoBookingAmount bookingAmount;

    @Embedded
    private RouteSpecification routeSpecification;

    @Embedded
    private Itinery itinery;

    @Embedded
    private Delivery delivery;

    // Constructor
    public Cargo(BookingId bookingId, RouteSpecification routeSpecification) {
        this.bookingId = bookingId;
        this.origin = routeSpecification.getOrigin();
        this.routeSpecification = routeSpecification;
    }

    // Business Methods
    public void deriveDeliveryProgress() {
        // Implementation goes here
    }

    public void assignToRoute(Itinery itinery) {
        // Implementation goes here
    }
}
