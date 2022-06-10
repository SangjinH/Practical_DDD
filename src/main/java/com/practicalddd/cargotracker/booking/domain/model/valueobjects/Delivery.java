package com.practicalddd.cargotracker.booking.domain.model.valueobjects;

import com.practicalddd.cargotracker.booking.domain.model.entities.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Embeddable
@NoArgsConstructor
@Getter
public class Delivery {

    public static final Date ETA_UNKOWN = null;

    //Enumerated Types - Routing Status / Transport Status of the Cargo
    @Enumerated(EnumType.STRING)
    @Column(name = "routing_status")
    private RoutingStatus routingStatus; //[NOT_ROUTED, ROUTED, MISROUTED]
    @Enumerated(EnumType.STRING)
    @Column(name = "transport_status")
    private TransportStatus transportStatus; //[NOT_RECEIVED, IN_PORT, ONBOARD_CARRIER, CLAIMED, UNKNOWN]

    //Current/PRevious information of the Cargo. Helps the operator in determining the current state is OK.
    @Column(name = "last_known_location_id")
    @AttributeOverride(name = "unLocCode", column = @Column(name = "last_known_location_id"))
    private Location lastKnownLocation;
    @Column(name = "current_voyage_number")
    @AttributeOverride(name = "voyageNumber", column = @Column(name = "current_voyage_number"))
    private Voyage currentVoyage;
    @Embedded
    private LastCargoHandledEvent lastEvent;

    //화물 활동에 대한 예측. 운영자가 미래를 위해 변경해야 할 사항이 있는지 판단하는 데 도움을 줍니다.
    @Embedded
    public static CargoHandlingActivity NO_ACTIVITY = new CargoHandlingActivity();
    @Embedded
    private CargoHandlingActivity nextExpectedActivity;

    public CargoHandlingActivity getNO_ACTIVITY() {
        return NO_ACTIVITY;
    }

    public void setNO_ACTIVITY(CargoHandlingActivity NO_ACTIVITY) {
        this.NO_ACTIVITY = NO_ACTIVITY;
    }

    public Delivery(LastCargoHandledEvent lastEvent, CargoItinerary itinerary,
                    RouteSpecification routeSpecification) {
        this.lastEvent = lastEvent;

        this.routingStatus = calculateRoutingStatus(itinerary,
                routeSpecification);
        this.transportStatus = calculateTransportStatus();
        this.lastKnownLocation = calculateLastKnownLocation();
        this.currentVoyage = calculateCurrentVoyage();
    }



    public static Delivery deriveFrom(RouteSpecification routeSpecification, CargoItinerary itinerary, LastCargoHandledEvent lastCargoHandledEvent) {
        return new Delivery(lastCargoHandledEvent, itinerary, routeSpecification);
    }
    /**
     * Method to calculate the Transposrt Status of a Cargo
     * @return
     */
    private TransportStatus calculateTransportStatus() {
        if (lastEvent.getHandlingEventType() == null) {
            return TransportStatus.NOT_RECEIVED;
        }

        switch (lastEvent.getHandlingEventType()) {
            case "LOAD":
                return TransportStatus.ONBOARD_CARRIER;
            case "UNLOAD":
            case "RECEIVE":
            case "CUSTOMS":
                return TransportStatus.IN_PORT;
            case "CLAIM":
                return TransportStatus.CLAIMED;
            default:
                return TransportStatus.UNKNOWN;
        }



    }


    /**
     * Creates a new delivery snapshot to reflect changes in routing, i.e. when
     * the route specification or the itinerary has changed but no additional
     * handling of the cargo has been performed.
     */
    public Delivery updateOnRouting(RouteSpecification routeSpecification,
                                    CargoItinerary itinerary) {


        return new Delivery(this.lastEvent, itinerary, routeSpecification);
    }

    /**
     *
     * @param routeSpecification
     * @param itinerary
     * @param lastCargoHandledEvent
     * @return
     */
    public static Delivery derivedFrom(RouteSpecification routeSpecification,
                                       CargoItinerary itinerary, LastCargoHandledEvent lastCargoHandledEvent) {

        return new Delivery(lastCargoHandledEvent, itinerary, routeSpecification);
    }





    /**
     * Method to calculate the Routing status of a Cargo
     *
     * @param itinerary
     * @param routeSpecification
     * @return
     */
    private RoutingStatus calculateRoutingStatus(CargoItinerary itinerary,
                                                 RouteSpecification routeSpecification) {
        if (itinerary == null || itinerary == CargoItinerary.EMPTY_ITINERARY) {
            return RoutingStatus.NOT_ROUTED;
        } else {
            return RoutingStatus.ROUTED;
        }
    }

    /**
     * Calculate Last known location
     * @return
     */
    private Location calculateLastKnownLocation() {
        if (lastEvent != null) {
            return new Location(lastEvent.getHandlingEventLocation());
        } else {
            return null;
        }
    }

    /**
     *
     * @return
     */
    private Voyage calculateCurrentVoyage() {
        if (getTransportStatus().equals(TransportStatus.ONBOARD_CARRIER) && lastEvent != null) {
            return new Voyage(lastEvent.getHandlingEventVoyage());
        } else {
            return null;
        }
    }
}
