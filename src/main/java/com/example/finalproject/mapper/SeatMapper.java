package com.example.finalproject.mapper;


import com.example.finalproject.dto.hall.ResponseSeatDto;
import com.example.finalproject.model.Seat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    ResponseSeatDto toDto(Seat seat);

    List<ResponseSeatDto> toDtoList(List<Seat> seats);
}
