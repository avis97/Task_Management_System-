package com.JavaTask.TaskManageMent.Service;

import com.JavaTask.TaskManageMent.Entitys.User;
import com.JavaTask.TaskManageMent.Exception.UserNotFoundException;
import com.JavaTask.TaskManageMent.RequestDto.UserRequestDto;
import com.JavaTask.TaskManageMent.ResponseDto.TaskResponseDto;
import com.JavaTask.TaskManageMent.ResponseDto.UserResponseDto;

import java.util.List;

public interface UserService{
    UserResponseDto addUser(UserRequestDto requestDto);
    List<UserResponseDto> getAllUser() throws UserNotFoundException;
    UserResponseDto getUserById(int userId) throws UserNotFoundException;
    UserResponseDto deleteUser(int userId) throws UserNotFoundException;
    UserResponseDto registerNewUser(UserRequestDto userRequestDtos);
    List<TaskResponseDto> getAllTaskByUserId(int userId) throws UserNotFoundException;
}
