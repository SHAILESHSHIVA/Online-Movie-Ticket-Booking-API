package com.bms.BMS.repository;

import com.bms.BMS.models.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository  extends JpaRepository<TicketModel,Integer> {

//    @Query(value = "delete from tickets where ticket_id= :id",nativeQuery = true)
//    int cancelTicket(String id);
}
