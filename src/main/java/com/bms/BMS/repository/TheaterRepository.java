package com.bms.BMS.repository;

import com.bms.BMS.models.TheaterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterModel,Integer> {

    @Query(value = "select location from theaters where name=:theaterName",nativeQuery = true)
    HashSet<String> getLocation(String theaterName);

}
