package com.JavaTask.TaskManageMent.Service.impl;

import com.JavaTask.TaskManageMent.Entitys.User;
import com.JavaTask.TaskManageMent.Exception.UserNotFoundException;
import com.JavaTask.TaskManageMent.Repository.UserRepository;
import com.JavaTask.TaskManageMent.RequestDto.UserRequestDto;
import com.JavaTask.TaskManageMent.ResponseDto.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest{

    //Junit Test in Service Layer.
    @Autowired
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;
    User user;
    @BeforeEach
    void setUp(){
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
    }

    @Test
    void getAllUser() throws UserNotFoundException {
        List<UserResponseDto> users = userService.getAllUser();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    void getUserById() throws UserNotFoundException {
        UserResponseDto responseDto=userService.getUserById(1);
        int id1=responseDto.getUserId();
        assertNotNull(id1);
        assertEquals(id1,1);
    }
}