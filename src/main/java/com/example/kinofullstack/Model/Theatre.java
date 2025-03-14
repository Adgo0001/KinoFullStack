package com.example.kinofullstack.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "theatres")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "theatre_id")
    private int id;

    @Column(name = "name")
    private String theatreName;

    @Column(name = "num_rows")
    private int rows;

    @Column(name = "seats_per_row")
    private int seatsPerRow;

    public Theatre() {}

    public Theatre(String theatreName, int rows, int seatsPerRow) {
        this.theatreName = theatreName;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
    }


    public int getId() {
        return id;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public int getRows() {
        return rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

}
