package com.bms.BMS.controller;

import com.bms.BMS.entryDTOs.UserEntryDto;
import com.bms.BMS.models.TicketModel;
import com.bms.BMS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto) {


        try{
             String response = userService.addUser(userEntryDto);
            return new ResponseEntity<String>(response,HttpStatus.CREATED);

        }
        catch( Exception e){
            String res = "User Not Added";
            System.out.println(e);
            return new ResponseEntity<String>(res, HttpStatus.BAD_REQUEST);
        }


    }

}


