package com.practicalddd.cargotracker.booking.domain.model.aggregates;

import com.practicalddd.cargotracker.booking.domain.model.commands.BookCargoCommand;
import com.practicalddd.cargotracker.booking.domain.model.entities.Location;
import com.practicalddd.cargotracker.booking.domain.model.valueobjects.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Cargo implements Serializable {

    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Embedded
    private BookingId bookingId; // Aggregate 판별 Id

    @Embedded
    private BookingAmount bookingAmount; // Booking 총 금액

    @Embedded
    private Location origin; // 물품의 출발지

    @Embedded
    private RouteSpecification routeSpecification; // 물품의 상세경로

    @Embedded
    private CargoItinerary itinerary; // 예정된 일정

    @Embedded
    private Delivery delivery; // 물품의 배송지

    /**
     * 생성자를 만드는데, Command Handler를 통해서 다른 Bounded Context에도 명령을 전달하고,
     * 초기 설정을 하는 생성자
     */
    public Cargo(BookCargoCommand bookCargoCommand) {

        this.bookingId = new BookingId(bookCargoCommand.getBookingId());
        this.routeSpecification = new RouteSpecification(
                new Location(bookCargoCommand.getOriginLocation()),
                new Location(bookCargoCommand.getDestLocation()),
                bookCargoCommand.getDestArrivalDeadline()
        );
        this.origin = routeSpecification.getOrigin();
        this.bookingAmount = new BookingAmount(bookCargoCommand.getBookingAmount());
        this.itinerary = CargoItinerary.EMPTY_ITINERARY; // 처음 물거늘 구입하면 바로 경로가 설정되진 않음
        this.delivery = Delivery.deriveFrom(this.routeSpecification,
                this.itinerary, LastCargoHandledEvent.EMPTY);
    }


    // Business Methods
    public void deriveDeliveryProgress() {
        // Implementation goes here
    }

    public void assignToRoute(CargoItinerary cargoItinerary) {
        this.itinerary = cargoItinerary;
    }
}
