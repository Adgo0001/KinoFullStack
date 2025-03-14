package com.example.kinofullstack.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "reservedseats")
public class ReservedSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "reserved_seat_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Column(name = "r_number")
    private int rowNumber;

    @Column(name = "seat_number")
    private int seatNumber;

    public ReservedSeat() {}

    public ReservedSeat(Reservation reservation, int rowNumber, int seatNumber) {
        this.reservation = reservation;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
