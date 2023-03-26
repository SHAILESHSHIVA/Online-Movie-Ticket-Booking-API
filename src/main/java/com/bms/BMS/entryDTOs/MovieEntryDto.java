package com.bms.BMS.entryDTOs;


import com.bms.BMS.enums.Genre;
import com.bms.BMS.enums.Language;
import lombok.Data;

@Data
public class MovieEntryDto {

    private String movieName;

    private double ratings;

    private int duration;   

    private Language language;

    private Genre genre;


}
