package com.bms.BMS.convertor;

import com.bms.BMS.entryDTOs.UserEntryDto;
import com.bms.BMS.models.UserModel;

public class UserConvertor {

    public static UserModel dtoToEntity(UserEntryDto userEntryDto){

        UserModel userEntity =  UserModel.builder().age(userEntryDto.getAge()).address(userEntryDto.getAddress()).email(userEntryDto.getEmail())
                .name(userEntryDto.getName()).mobNo(userEntryDto.getMobNo()).build();

        return userEntity;



    }


}
