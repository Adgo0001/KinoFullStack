package com.example.kinofullstack.controller;

import com.example.kinofullstack.model.Movie;
import com.example.kinofullstack.model.Show;
import com.example.kinofullstack.model.Reservation;
import com.example.kinofullstack.model.Theatre;
import com.example.kinofullstack.repository.MovieRepository;
import com.example.kinofullstack.repository.ShowRepo;
import com.example.kinofullstack.repository.ReservationsRepo;
import com.example.kinofullstack.repository.TheatresRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/shows")
@CrossOrigin(origins = "*") // Allow frontend requests
public class TicketController {

    private final ShowRepo showRepository;
    private final MovieRepository movieRepository;
    private final TheatresRepo theatreRepository;
    private final ReservationsRepo reservationRepository;

    public TicketController(ShowRepo showRepository, MovieRepository movieRepository,
                            TheatresRepo theatreRepository, ReservationsRepo reservationRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllShows() {
        List<Show> shows = showRepository.findAll();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Show show : shows) {
            Optional<Movie> movie = movieRepository.findById(show.getMovieId());
            Optional<Theatre> theatre = theatreRepository.findById(show.getTheatreId());

            if (movie.isPresent() && theatre.isPresent()) {
                Map<String, Object> showDetails = new HashMap<>();
                showDetails.put("id", show.getId());
                showDetails.put("movieTitle", movie.get().getTitle());
                showDetails.put("showDate", show.getShowDate());
                showDetails.put("showTime", show.getShowTime());
                showDetails.put("theatreName", theatre.get().getTheatreName());
                showDetails.put("theatreId", show.getTheatreId());

                response.add(showDetails);
            }
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/theatres/{theatreId}/shows/{showId}/seats")
    public ResponseEntity<int[][]> getAvailableSeats(@PathVariable int theatreId, @PathVariable int showId) {
        System.out.println("Received request for seats - theatreId: " + theatreId + ", showId: " + showId);

        Optional<Theatre> theatreOpt = theatreRepository.findById(theatreId);
        if (theatreOpt.isEmpty()) {
            System.out.println("Theatre not found with ID: " + theatreId);
            return ResponseEntity.notFound().build();
        }

        Theatre theatre = theatreOpt.get();
        int rows = theatre.getRows();
        int seatsPerRow = theatre.getSeatsPerRow();
        int[][] seatLayout = new int[rows][seatsPerRow];

        List<Reservation> reservations = reservationRepository.findByShowId(showId);
        for (Reservation res : reservations) {
            res.getReservedSeats().forEach(seat -> {

                if (seat.getRowNumber() < rows && seat.getSeatNumber() < seatsPerRow) {
                    seatLayout[seat.getRowNumber()][seat.getSeatNumber()] = 1;
                }
            });
        }

        System.out.println("Returning seat layout: " + Arrays.deepToString(seatLayout));
        return ResponseEntity.ok(seatLayout);

    }


    @PostMapping("/reservations")
    public ResponseEntity<String> bookTickets(@RequestBody Map<String, Object> request) {
        try {
            int showId = (int) request.get("showId");
            String customerName = (String) request.get("customerName");
            String customerPhone = (String) request.get("customerPhone");
            List<String> seats = (List<String>) request.get("seats");

            Optional<Show> showOpt = showRepository.findById(showId);
            if (showOpt.isEmpty()) return ResponseEntity.badRequest().body("Show not found");

            // Create new reservation
            Reservation reservation = new Reservation();
            reservation.setShow(showOpt.get());
            reservation.setCustomerName(customerName);
            reservation.setCustomerPhone(customerPhone);
            reservation.setStatus("Confirmed");

            // Convert seat format ["0-2", "1-5"] → Row & Seat
            seats.forEach(seat -> {
                String[] parts = seat.split("-");
                int row = Integer.parseInt(parts[0]);
                int seatNumber = Integer.parseInt(parts[1]);
                reservation.addReservedSeat(row, seatNumber);
            });

            reservationRepository.save(reservation);
            return ResponseEntity.ok("Booking successful!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error booking ticket: " + e.getMessage());
        }
    }
}