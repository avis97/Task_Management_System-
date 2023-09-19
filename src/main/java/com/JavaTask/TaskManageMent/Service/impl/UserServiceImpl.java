package com.JavaTask.TaskManageMent.Service.impl;
import com.JavaTask.TaskManageMent.Converter.TaskConverter;
import com.JavaTask.TaskManageMent.Converter.UserConverter;
import com.JavaTask.TaskManageMent.Entitys.Task;
import com.JavaTask.TaskManageMent.Entitys.User;
import com.JavaTask.TaskManageMent.Exception.UserNotFoundException;
import com.JavaTask.TaskManageMent.Repository.UserRepository;
import com.JavaTask.TaskManageMent.RequestDto.UserRequestDto;
import com.JavaTask.TaskManageMent.ResponseDto.TaskResponseDto;
import com.JavaTask.TaskManageMent.ResponseDto.UserResponseDto;
import com.JavaTask.TaskManageMent.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public UserResponseDto addUser(UserRequestDto requestDto){
        User user= UserConverter.UserRequestDtoToUser(requestDto);
        userRepository.save(user);
        UserResponseDto userResponseDto=UserConverter.UserToUserResponseDto(user);
        return userResponseDto;
    }
    public List<UserResponseDto> getAllUser() throws UserNotFoundException {
        System.out.println("Hello");
        List<User> userList=userRepository.findAll();
        List<UserResponseDto> listOfUser=new ArrayList<>();
        for(User user:userList){
            UserResponseDto userResponseDto;
            try {
                userResponseDto = UserConverter.UserToUserResponseDto(user);
            }catch (Exception e){
                throw new UserNotFoundException("User Does Not Exist");
            }
            listOfUser.add(userResponseDto);
        }
        return listOfUser;
    }
    public UserResponseDto getUserById(int userId) throws UserNotFoundException {
        System.out.println("Hello With Cacha");
        User user;
        try{
            user=userRepository.findById(userId).get();
        }catch(Exception e){
            throw new UserNotFoundException("User Id Is Invalid");
        }
        UserResponseDto userResponseDto=UserConverter.UserToUserResponseDto(user);

        return  userResponseDto;
    }
    public UserResponseDto deleteUser(int userId) throws UserNotFoundException {
        User user;
        try{
            user=userRepository.findById(userId).get();
        }catch (Exception e){
            throw new UserNotFoundException("User Is Not Found!!");
        }
        userRepository.delete(user);
        UserResponseDto userResponseDto=UserConverter.UserToUserResponseDto(user);
        return userResponseDto;
    }
    public UserResponseDto registerNewUser(UserRequestDto userRequestDtos){
        User user=UserConverter.UserRequestDtoToUser(userRequestDtos);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        UserResponseDto responseDtos=UserConverter.UserToUserResponseDto(user);
        return responseDtos;
    }
    public List<TaskResponseDto> getAllTaskByUserId(int userId) throws UserNotFoundException {
        User user;
        try{
            user=userRepository.findById(userId).get();
        }catch(Exception e){
            throw new UserNotFoundException("User Not Found");
        }
        List<Task> tasks=user.getTaskList();
        List<TaskResponseDto> TaskList=new ArrayList<>();
        for(Task task:tasks){
            TaskResponseDto taskResponseDto= TaskConverter.TaskToTaskResponseDto(task);
            TaskList.add(taskResponseDto);
        }
        return TaskList;
    }
}
