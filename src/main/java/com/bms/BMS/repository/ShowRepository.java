package com.bms.BMS.repository;

import com.bms.BMS.models.ShowModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<ShowModel,Integer> {

//    @Query(value = "select * from shows  where movie_entity_id= :movieId and theater_entity_id= :theaterId",nativeQuery = true)
//    List<LocalTime> getShowTime(int movieId, int theaterId);

}
