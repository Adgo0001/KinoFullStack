package com.example.kinofullstack.Model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "shows")
@CrossOrigin(origins = "")
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

    public void setMovieId(int movieId) {
    }

    public int getMovieId() {
        return movieId;
    }

    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }
}