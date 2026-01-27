
package com.example.finalproject.dto.hall;

import com.example.finalproject.model.Hall;
import com.example.finalproject.model.Seat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HallMapper {

    HallResponseDto toDto(Hall hall);

    ResponseSeatDto toSeatDto(Seat seat);
}

