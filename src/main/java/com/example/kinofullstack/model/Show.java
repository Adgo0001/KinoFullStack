package com.example.kinofullstack.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "show_id")
    private int id;

    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "theatre_id")
    private int theatreId;

    @Column(name = "show_date")
    private LocalDate showDate;

    @Column(name = "show_time")
    private LocalTime showTime;

    public Show() {}

    public Show(int movieId, int theatreId, LocalDate showDate, LocalTime showTime) {
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.showDate = showDate;
        this.showTime = showTime;
    }

    public int getId() {
        return id;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public LocalTime getShowTime() {
        return showTime;
    }
}
