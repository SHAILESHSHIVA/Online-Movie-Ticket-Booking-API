package com.bms.BMS.convertor;

import com.bms.BMS.entryDTOs.ShowEntryDto;
import com.bms.BMS.models.ShowModel;

public class ShowConvertor {

    public static ShowModel dtoToEntity(ShowEntryDto showEntryDto){

        ShowModel showModel = ShowModel.builder().showDate(showEntryDto.getLocalDate())
                .showTime(showEntryDto.getLocalTime()).showType(showEntryDto.getShowType())
                .build();

        return showModel;
    }
}
