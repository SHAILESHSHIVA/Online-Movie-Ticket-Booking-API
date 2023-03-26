package com.bms.BMS.controller;

import com.bms.BMS.entryDTOs.ShowEntryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bms.BMS.services.ShowService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody  ShowEntryDto showEntryDto){

        String response = showService.addShows(showEntryDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/showtime")
    public ResponseEntity<List<LocalTime>> showTime(@RequestParam ("movieId") String movieId, @RequestParam("theaterId") String theaterId,
                                                    @RequestParam("date")
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){


            List<LocalTime> response = showService.getShowTime(Integer.parseInt(movieId),Integer.parseInt(theaterId),date);
            return new ResponseEntity<>(response,HttpStatus.FOUND);


    }

    @GetMapping("/showsInTheater")
    public ResponseEntity<Integer> showsOnGivenDate(@RequestParam("theaterId") String theaterId,
                                                    @RequestParam("date")
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){


        int response = showService.showsOnGivenDate(Integer.parseInt(theaterId),date);
        return new ResponseEntity<>(response,HttpStatus.FOUND);


    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeShow(@RequestParam("showId") int showId){

        String response = showService.removeShow(showId);

        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);

    }











}
