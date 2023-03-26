package com.bms.BMS.services;

import com.bms.BMS.convertor.UserConvertor;
import com.bms.BMS.models.TicketModel;
import com.bms.BMS.models.UserModel;
import com.bms.BMS.entryDTOs.UserEntryDto;
import com.bms.BMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto) throws Exception {

        UserModel usermodel = UserConvertor.dtoToEntity(userEntryDto);

        userRepository.save(usermodel);

        return " User added successfully";
    }



}
