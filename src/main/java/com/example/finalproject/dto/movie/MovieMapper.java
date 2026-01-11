package com.example.finalproject.dto.movie;
import com.example.finalproject.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "movieId", ignore = true)
    @Mapping(target = "movieGenres", ignore = true)
    @Mapping(target = "movieCastMembers", ignore = true)
    Movie toEntity(CreatMovieDTO dto);


    ReadMovieDTO toReadDto(Movie movie);
}
