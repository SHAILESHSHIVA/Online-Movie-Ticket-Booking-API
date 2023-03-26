package com.bms.BMS.entryDTOs;

import lombok.Data;

import java.util.*;

@Data
public class TicketEntryDto {

    private int userId;
    private int showId;
    private List<String> requestedSeats = new ArrayList<>();



}
