package com.example.finalproject.mapper;
import com.example.finalproject.dto.user.RequestUserDTO;
import com.example.finalproject.dto.user.ResponseUserDTO;
import com.example.finalproject.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User requestUser(RequestUserDTO requestUserDTO);
    ResponseUserDTO responseUser(User user);
}
