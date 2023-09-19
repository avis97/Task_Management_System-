package com.JavaTask.TaskManageMent.Converter;

import com.JavaTask.TaskManageMent.Entitys.User;
import com.JavaTask.TaskManageMent.RequestDto.UserRequestDto;
import com.JavaTask.TaskManageMent.ResponseDto.UserResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter{
    public static User UserRequestDtoToUser(UserRequestDto userRequestDto){
        return User.builder()
                .fullName(userRequestDto.getFullName())
                .userName(userRequestDto.getUserName())
                .userPhnNumber(userRequestDto.getUserPhnNumber())
                .userAddress(userRequestDto.getUserAddress())
                .password(userRequestDto.getPassword())
                .build();
    }
    public static UserResponseDto UserToUserResponseDto(User user){
        return UserResponseDto.builder()
                .fullName(user.getFullName())
                .userAddress(user.getUserAddress())
                .userPhnNumber(user.getUserPhnNumber())
                .userName(user.getUsername())
                .userId(user.getUserId())
                .build();
    }
}
