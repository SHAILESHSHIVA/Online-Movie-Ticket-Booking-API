package com.bms.BMS.services;


import com.bms.BMS.convertor.MovieConvertor;
import com.bms.BMS.entryDTOs.MovieEntryDto;
import com.bms.BMS.models.MovieModel;
import com.bms.BMS.models.ShowModel;
import com.bms.BMS.models.TicketModel;
import com.bms.BMS.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto){

        MovieModel movieModel = MovieConvertor.dtoToEntity(movieEntryDto);
//        System.out.println(movieModel.getRatings());

        movieRepository.save(movieModel);



        return "Movie added successfully";
    }

    public String getMovieById(int id) {

        MovieModel movie = movieRepository.findById(id).get();
        String movieName = movie.getMovieName();

        return movieName;

    }

    public String maxShows(){
        List<MovieModel> movieLst = movieRepository.findAll();
        int max =0;
        String movieName = "";

        for(MovieModel movie:movieLst){
            List<ShowModel> showlist = movie.getShowEntityList();
            if(showlist.size() > max){
                max = showlist.size();
                movieName = movie.getMovieName();
            }
        }

        return movieName;
    }

    public int totalCollection(int id){
        MovieModel movie = movieRepository.findById(id).get();

        List<ShowModel> showList = movie.getShowEntityList();
        int amount =0;

        for(ShowModel show:showList){
            List<TicketModel> ticketList = show.getListOfBookedTickets();
            for(TicketModel tkt:ticketList){
                amount += tkt.getTotalAmount();
            }

        }

        return amount;
    }



}
