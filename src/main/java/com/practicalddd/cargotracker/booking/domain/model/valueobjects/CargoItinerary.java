package com.practicalddd.cargotracker.booking.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class CargoItinerary {

    @Embedded
    public static CargoItinerary EMPTY_ITINERARY = new CargoItinerary();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cargo_id")
    private List<Leg> legs = List.of();

    public CargoItinerary getEMPTY_ITINERARY() {
        return EMPTY_ITINERARY;
    }
}
