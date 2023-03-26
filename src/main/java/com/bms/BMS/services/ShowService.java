package com.bms.BMS.services;

import com.bms.BMS.convertor.ShowConvertor;
import com.bms.BMS.entryDTOs.ShowEntryDto;
import com.bms.BMS.enums.SeatType;
import com.bms.BMS.models.*;
import com.bms.BMS.repository.MovieRepository;
import com.bms.BMS.repository.ShowRepository;
import com.bms.BMS.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private ShowRepository showRepository;


    public String addShows(ShowEntryDto showEntryDto) {

        ShowModel showModel = ShowConvertor.dtoToEntity(showEntryDto);

        int movieId = showEntryDto.getMovieId();
        int theaterId = showEntryDto.getTheaterId();

        MovieModel movieModel = movieRepository.findById(movieId).get();
        TheaterModel theaterModel = theaterRepository.findById(theaterId).get();

        showModel.setMovieEntity(movieModel);
        showModel.setTheaterEntity(theaterModel);

        List<ShowSeatModel> showSeatModelList = createShowSeat(showEntryDto, showModel);
        showModel.setListOfShowSeats(showSeatModelList);

        showModel = showRepository.save(showModel);

        movieModel.getShowEntityList().add(showModel);
        theaterModel.getShowEntityList().add(showModel);

        movieRepository.save(movieModel);
        theaterRepository.save(theaterModel);

        return "Show Added Successfully";
    }


    public List<ShowSeatModel> createShowSeat(ShowEntryDto showEntryDto, ShowModel showModel) {


        TheaterModel theaterEntity = showModel.getTheaterEntity();

        List<TheaterSeatModel> theaterSeatEntityList = theaterEntity.getTheaterSeatEntityList();

        List<ShowSeatModel> seatEntityList = new ArrayList<>();

        for (TheaterSeatModel theaterSeatEntity : theaterSeatEntityList) {

            ShowSeatModel showSeatEntity = new ShowSeatModel();

            showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());
            showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());

            if (theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC))
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());

            else
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());

            showSeatEntity.setBooked(false);
            showSeatEntity.setShowEntity(showModel);

            seatEntityList.add(showSeatEntity);
        }

        return seatEntityList;


    }


    public List<LocalTime> getShowTime(int movieId, int theaterId, LocalDate date){

//        List<LocalTime> time = showRepository.getShowTime(movieId, theaterId);
//        return time;
        TheaterModel theaterModel = theaterRepository.findById(theaterId).get();

        List<ShowModel> lstOfShows = theaterModel.getShowEntityList();
        List<LocalTime> showTime = new ArrayList<>();

        for(ShowModel showModel : lstOfShows){

            if(showModel.getMovieEntity().getId() == movieId && showModel.getShowDate().equals(date)){
                showTime.add(showModel.getShowTime());
            }
        }

        return showTime;



    }

    public int showsOnGivenDate(int theaterId, LocalDate date){

//        List<LocalTime> time = showRepository.getShowTime(movieId, theaterId);
//        return time;
        TheaterModel theaterModel = theaterRepository.findById(theaterId).get();

        List<ShowModel> lstOfShows = theaterModel.getShowEntityList();

        int count =0;

        for(ShowModel showModel : lstOfShows){

            if(showModel.getShowDate().equals(date)){
                count++;

            }
        }

        return count;



    }

    public String removeShow(int id){

        ShowModel showModel = showRepository.findById(id).get();

        showRepository.delete(showModel);

        return "show removed";

    }
}