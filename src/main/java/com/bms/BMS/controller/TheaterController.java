package com.bms.BMS.controller;

import com.bms.BMS.entryDTOs.TheaterEntryDto;
import com.bms.BMS.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheater(@RequestBody TheaterEntryDto theaterEntryDto){

        try{
            String response = theaterService.addTheater(theaterEntryDto);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/locations")
    public ResponseEntity<HashSet<String>> findLocation(@RequestParam("movie") String theater){

        HashSet<String> location = theaterService.getLocation(theater);

        return new ResponseEntity<>(location,HttpStatus.FOUND);

    }

}
