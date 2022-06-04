package com.practicalddd.cargotracker.booking.domain.model.valueobjects;

import com.practicalddd.cargotracker.booking.domain.model.entities.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@NoArgsConstructor
@Getter
public class CargoHandlingActivity {

    private static final long serialVersionUID = 1L;

    @Column(name = "next_expected_handling_event_type")
    private String type;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "next_expected_location_id"))
    private Location location;
    @Column(name = "next_expected_voyage_id")
    @AttributeOverride(name = "voyageNumber", column = @Column(name = "next_expected_voyage_id"))
    private Voyage voyage;

    public CargoHandlingActivity(String type, Location location) {
        type = type;
        location = location;
    }

    public CargoHandlingActivity(String type, Location location, Voyage voyage) {
        type = type;
        location = location;
        voyage = voyage;
    }
}
