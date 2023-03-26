package com.bms.BMS.services;

import com.bms.BMS.convertor.TheaterConvertor;
import com.bms.BMS.entryDTOs.TheaterEntryDto;
import com.bms.BMS.enums.SeatType;
import com.bms.BMS.models.TheaterModel;
import com.bms.BMS.models.TheaterSeatModel;
import com.bms.BMS.repository.TheaterRepository;
import com.bms.BMS.repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    public String addTheater(TheaterEntryDto theaterEntryDto) throws Exception{

        if(theaterEntryDto.getName()==null || theaterEntryDto.getLocation()==null){
            throw new Exception("Name or Location cant be empty");
        }

        TheaterModel theaterModel = TheaterConvertor.dtoToEntity(theaterEntryDto);
        List<TheaterSeatModel> theaterSeatModelList = createSeats(theaterEntryDto,theaterModel);

        theaterModel.setTheaterSeatEntityList(theaterSeatModelList);
        theaterRepository.save(theaterModel);

        return "Theater added successfully";


    }



    private List<TheaterSeatModel> createSeats(TheaterEntryDto theaterEntryDto, TheaterModel theaterModel){

        int classicSeats = theaterEntryDto.getClassicSeatsCount();
        int premiumSeats = theaterEntryDto.getPremiumSeatsCount();

        List<TheaterSeatModel> theaterSeatModelList = new ArrayList<>();

        for(int count = 1; count <= classicSeats;count++){

            TheaterSeatModel theaterSeatModel = TheaterSeatModel.builder().seatNo(count+"C")
                    .seatType(SeatType.CLASSIC)
                    .theaterEntity(theaterModel).build();

            theaterSeatModelList.add(theaterSeatModel);
//            System.out.println(theaterSeatModel);

        }

        for(int count = 1; count <= premiumSeats;count++){

            TheaterSeatModel theaterSeatModel = TheaterSeatModel.builder().seatNo(count+"P")
                    .seatType(SeatType.PREMIUM)
                    .theaterEntity(theaterModel).build();

            theaterSeatModelList.add(theaterSeatModel);
//            System.out.println(theaterSeatModel);

        }

        return theaterSeatModelList;

    }

    public HashSet<String> getLocation(String theater){
        HashSet<String> location = theaterRepository.getLocation(theater);
        return location;
    }
}
