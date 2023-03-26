package com.bms.BMS.controller;


import com.bms.BMS.entryDTOs.MovieEntryDto;
import com.bms.BMS.models.MovieModel;
import com.bms.BMS.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity addMovie(@RequestBody MovieEntryDto movieEntryDto){

        try{
            String response = movieService.addMovie(movieEntryDto);
//            System.out.println(movieEntryDto.getRatings());
            return new ResponseEntity<String>(response, HttpStatus.CREATED);

        }
        catch( Exception e){
            String res = "Movie Not Added";
            return new ResponseEntity<String>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getmovie/{id}")
    public ResponseEntity<String> getMovieById(@PathVariable String id){
        try{
            String movie = movieService.getMovieById(Integer.valueOf(id));
            return new ResponseEntity<>(movie,HttpStatus.OK);
        }catch(Exception e){

            String res = "movie not present by this id";
            return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/maxmovie")
    public ResponseEntity<String> maxShows(){
        String movie = movieService.maxShows();
        return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }

    @GetMapping("/collection/{id}")
    public ResponseEntity<Integer> totalCollection(@PathVariable String id){
            int amount = movieService.totalCollection(Integer.valueOf(id));
            return new ResponseEntity<>(amount,HttpStatus.FOUND);

    }













}
