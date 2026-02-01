
package com.example.finalproject.mapper;

import com.example.finalproject.dto.hall.HallResponseDto;
import com.example.finalproject.dto.hall.ResponseSeatDto;
import com.example.finalproject.model.Hall;
import com.example.finalproject.model.Seat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HallMapper {

    HallResponseDto toDto(Hall hall);

    ResponseSeatDto toSeatDto(Seat seat);
}

