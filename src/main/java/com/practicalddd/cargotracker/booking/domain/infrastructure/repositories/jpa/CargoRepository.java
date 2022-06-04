package com.practicalddd.cargotracker.booking.domain.infrastructure.repositories.jpa;

import com.practicalddd.cargotracker.booking.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.booking.domain.model.aggregates.Cargo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CargoRepository {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private final EntityManager em;

    public Cargo find(BookingId bookingId) {
        Cargo cargo;

        cargo = em.createQuery("select c from Cargo c where c.bookingId =:bookingId", Cargo.class)
                .setParameter("bookingId", bookingId)
                .getSingleResult();

        if (cargo == null) {
            new NoResultException();
            log.info("요청하신 ID로 존재하는 화물이 없습니다.");
            cargo = null;
        }

        return cargo;
    }

    public void create(Cargo cargo) {
        em.persist(cargo);
        em.flush();
    }

    public String getNextBookingId() {
        String random = UUID.randomUUID().toString().toUpperCase();
        return random.substring(0, random.indexOf("-"));
    }

    public List<Cargo> findAll() {
        return em.createQuery("select c from Cargo c", Cargo.class)
                .getResultList();
    }

    public List<BookingId> findAllBookingIds() {
        List<Cargo> cargos = em.createQuery("select c from Cargo c", Cargo.class)
                .getResultList();

        return cargos.stream()
                .map(Cargo::getBookingId)
                .collect(Collectors.toList());
    }
}
