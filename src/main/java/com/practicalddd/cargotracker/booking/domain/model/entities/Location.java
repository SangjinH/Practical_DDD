package com.practicalddd.cargotracker.booking.domain.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@Getter @Setter
public class Location {

    @Column(name = "origin_id")
    private String unLocCode;

    public Location(String unLocCode) {
        this.unLocCode = unLocCode;
    }
}
