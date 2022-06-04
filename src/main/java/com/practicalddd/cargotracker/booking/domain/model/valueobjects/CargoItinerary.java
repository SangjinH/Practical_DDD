package com.practicalddd.cargotracker.booking.domain.model.valueobjects;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Embeddable
@NoArgsConstructor
public class CargoItinerary {

    @Embedded
    public static CargoItinerary EMPTY_ITINERARY = new CargoItinerary();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cargo_id")
    private List<Leg> legs = List.of();


    public CargoItinerary getEMPTY_ITINERARY() {
        return EMPTY_ITINERARY;
    }

    public void setEMPTY_ITINERARY(CargoItinerary EMPTY_ITINERARY) {
        this.EMPTY_ITINERARY = EMPTY_ITINERARY;
    }
}
