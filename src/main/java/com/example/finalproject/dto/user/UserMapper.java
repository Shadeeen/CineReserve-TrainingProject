package com.example.finalproject.dto.user;
import com.example.finalproject.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User requestUser(RequestUserDTO requestUserDTO);
    ResponseUserDTO responseUser(User user);
}
