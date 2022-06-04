package com.practicalddd.cargotracker.booking.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class LastCargoHandledEvent {
    @Column(name = "last_handling_event_id")
    private Integer handlingEventId;
    @Column(name = "last_handling_event_type")
    private String handlingEventType;
    @Column(name = "last_handling_event_voyage")
    private String handlingEventVoyage;
    @Column(name = "last_handling_event_location")
    private String handlingEventLocation;

    // Null object pattern.
    @Embedded
    public static final LastCargoHandledEvent EMPTY = new LastCargoHandledEvent();
}
