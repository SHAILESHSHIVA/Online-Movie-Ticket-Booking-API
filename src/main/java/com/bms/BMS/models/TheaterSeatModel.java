package com.bms.BMS.models;



import com.bms.BMS.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "theater_seats")
@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class TheaterSeatModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private SeatType seatType;

    private String seatNo;

    @ManyToOne
    @JoinColumn
    private TheaterModel theaterEntity;





}
