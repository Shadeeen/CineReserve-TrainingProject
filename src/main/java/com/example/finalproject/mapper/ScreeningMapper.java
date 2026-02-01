package com.example.finalproject.mapper;

import com.example.finalproject.dto.screening.ScreeningResponseDto;
import com.example.finalproject.model.Screening;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScreeningMapper {

    @Mapping(source = "hall.hallId", target = "hallId")
    @Mapping(source = "hall.hallName", target = "hallName")
    @Mapping(source = "movie.movieId", target = "movieId")
    @Mapping(source = "movie.movieName", target = "movieName")
    @Mapping(source = "movie.duration", target = "movieDuration")
    ScreeningResponseDto toDto(Screening screening);

}
