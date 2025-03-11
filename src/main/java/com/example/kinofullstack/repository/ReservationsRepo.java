package com.example.kinofullstack.repository;

import com.example.kinofullstack.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationsRepo extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByShow_TheatreId(int theatreId);
    List<Reservation> findByShowId(int showId);
}
