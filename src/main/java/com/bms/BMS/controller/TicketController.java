package com.bms.BMS.controller;

import com.bms.BMS.entryDTOs.TicketEntryDto;
import com.bms.BMS.models.TicketModel;
import com.bms.BMS.services.TicketService;
import com.bms.BMS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;


    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody TicketEntryDto ticketEntryDto) {

        try {
            String response = ticketService.addTicket(ticketEntryDto);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            String response = e.getMessage();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/ticketBooked")
    public ResponseEntity<Map<Integer,String>> ticketBooked(@RequestParam("id") String id) {

        Map<Integer,String> response = ticketService.ticketBooked(Integer.parseInt(id));

        return new ResponseEntity<>(response, HttpStatus.FOUND);


    }

    @DeleteMapping("/cancelTicket")
    public ResponseEntity<String> cancelTicket(@RequestParam("id") int id){

        String response = ticketService.cancelTicket(id);

        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

}
