package com.bms.BMS.convertor;

import com.bms.BMS.entryDTOs.MovieEntryDto;
import com.bms.BMS.entryDTOs.UserEntryDto;
import com.bms.BMS.models.MovieModel;
import com.bms.BMS.models.UserModel;

public class MovieConvertor {

    public static MovieModel dtoToEntity(MovieEntryDto movieEntryDto){

        MovieModel movieEntity = MovieModel.builder().movieName(movieEntryDto.getMovieName()).duration(movieEntryDto.getDuration())
                .language(movieEntryDto.getLanguage()).genre(movieEntryDto.getGenre()).ratings(movieEntryDto.getRatings()).build();

        return movieEntity;



    }


}
