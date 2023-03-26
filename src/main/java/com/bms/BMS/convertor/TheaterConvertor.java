package com.bms.BMS.convertor;

import com.bms.BMS.entryDTOs.TheaterEntryDto;
import com.bms.BMS.models.TheaterModel;

public class TheaterConvertor {

    public static TheaterModel dtoToEntity(TheaterEntryDto theaterEntryDto){
        return TheaterModel.builder().location(theaterEntryDto.getLocation()).name(theaterEntryDto.getName())
                .build();
    }
}
