package com.bms.BMS.services;

import com.bms.BMS.convertor.TicketConvertor;
import com.bms.BMS.entryDTOs.TicketEntryDto;
import com.bms.BMS.models.ShowModel;
import com.bms.BMS.models.ShowSeatModel;
import com.bms.BMS.models.TicketModel;
import com.bms.BMS.models.UserModel;
import com.bms.BMS.repository.ShowRepository;
import com.bms.BMS.repository.TicketRepository;
import com.bms.BMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception{

        TicketModel ticketModel = TicketConvertor.dtoToEntity(ticketEntryDto);

        boolean validRequest = checkSeatAvailability(ticketEntryDto);

        if(validRequest == false )
            throw new Exception("Requested seat already booked");

        //Calculate Amount
        ShowModel showModel = showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeatModel> showSeatlist = showModel.getListOfShowSeats();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

//        int amount=0;
//
//        for(ShowSeatModel showSeatEntity : showSeatlist){
//            if(requestedSeats.contains(showSeatEntity.getSeatNo())){
//                amount = amount + showSeatModel.getPrice();
//                showSeat.setBooked(true);
//                showSeatModel.setBookedAt(new Date());
//            }
//        }
//        System.out.println(amount);
//        ticketModel.setTotalAmount(amount);


        int totalAmount = 0;
        for(ShowSeatModel showSeatEntity: showSeatlist){

            if(requestedSeats.contains(showSeatEntity.getSeatNo())){
                totalAmount += showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }
        }

        ticketModel.setTotalAmount(totalAmount);

        //setting other attributes of ticket entity

        ticketModel.setMovieName(showModel.getMovieEntity().getMovieName());
        ticketModel.setShowDate(showModel.getShowDate());
        ticketModel.setShowTime(showModel.getShowTime());
        ticketModel.setTheaterName(showModel.getTheaterEntity().getName());

        //set requested seats also
        String allotedSeats  = getAllottedSeats(requestedSeats);
        ticketModel.setBookedSeats(allotedSeats);

        //set foreign Attribute
        UserModel userModel = userRepository.findById(ticketEntryDto.getUserId()).get();

        ticketModel.setUserEntity(userModel);
        ticketModel.setShowEntity(showModel);

        ticketModel = ticketRepository.save(ticketModel);

        List<TicketModel> ticketModelList = showModel.getListOfBookedTickets();
        ticketModelList.add(ticketModel);
        showModel.setListOfBookedTickets(ticketModelList);

        showRepository.save(showModel);

        List<TicketModel> ticketModelList1 = userModel.getBookedTickets();
        ticketModelList1.add(ticketModel);
        userModel.setBookedTickets(ticketModelList1);

        userRepository.save(userModel);


        return"Ticket booked.";
    }

    public String getAllottedSeats(List<String> requestedSeats){

        String result = "";

        for(String seat:requestedSeats){
            result = result + seat + " ";
        }
        return result;

    }

    public boolean checkSeatAvailability(TicketEntryDto ticketEntryDto){

        int showId = ticketEntryDto.getShowId();

        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        ShowModel showModel = showRepository.findById(showId).get();

        List<ShowSeatModel> listOfShowSeats = showModel.getListOfShowSeats();

        for(ShowSeatModel showSeatModel : listOfShowSeats){

            String seatNo = showSeatModel.getSeatNo();

            if(requestedSeats.contains(seatNo)){
                if(showSeatModel.isBooked()){
                    return false;
                }
            }
        }

        return true;

    }

    public Map<Integer,String> ticketBooked(int id){

        UserModel user = userRepository.findById(id).get();

        List<TicketModel> list = user.getBookedTickets();
        Map<Integer,String> listOfTickets = new HashMap<>();

        for(TicketModel tkt : list){
            listOfTickets.put(tkt.getId(), tkt.getMovieName());
        }

        return listOfTickets;

    }

    public String cancelTicket(int id){

        TicketModel ticketModel = ticketRepository.findById(id).get();
        ShowModel show = ticketModel.getShowEntity();
        String[] bookedSeats = ticketModel.getBookedSeats().split(" ");

        List<ShowSeatModel> seats = show.getListOfShowSeats();
        for(ShowSeatModel seat : seats){
            String seatsNo = seat.getSeatNo();
            if(Arrays.asList(bookedSeats).contains(seatsNo)){
                seat.setBooked(false);
            }
        }

        ticketModel.setTotalAmount(0);
        ticketModel.setBookedSeats(null);

        ticketRepository.save(ticketModel);

//        ticketRepository.delete(ticketModel);
//
      return " Ticket Cancelled";
    }


}
