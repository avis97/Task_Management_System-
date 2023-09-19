package com.JavaTask.TaskManageMent.Controller;
import java.util.*;
import com.JavaTask.TaskManageMent.Exception.UserNotFoundException;
import com.JavaTask.TaskManageMent.RequestDto.UserRequestDto;
import com.JavaTask.TaskManageMent.ResponseDto.TaskResponseDto;
import com.JavaTask.TaskManageMent.ResponseDto.UserResponseDto;
import com.JavaTask.TaskManageMent.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@EnableCaching
public class UserController{

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/adduser")
    public ResponseEntity addUser(@RequestBody UserRequestDto requestDto){
        UserResponseDto user=userService.addUser(requestDto);
        return new ResponseEntity(user, HttpStatus.ACCEPTED);
    }
    @GetMapping("/getalluser")
    @Cacheable(value = "cacheInfo")
    public ResponseEntity getAllUser() throws UserNotFoundException{
        List<UserResponseDto> userList;
        try{
            userList=userService.getAllUser();
        }catch (UserNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userList,HttpStatus.ACCEPTED);
    }
    @GetMapping("/getuserByid/{userId}")
    @Cacheable(value = "cacheInfo")
    public ResponseEntity getUserById(@PathVariable int userId){
        UserResponseDto userResponseDto;
        try{
            userResponseDto=userService.getUserById(userId);
        }catch (UserNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        //return with all task...
        return new ResponseEntity(userResponseDto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/alltaskbyuserId/{userId}")
    @Cacheable(value = "cacheInfo")
    public ResponseEntity getAllTaskByUserId(@PathVariable("userId") int userId){
        List<TaskResponseDto> taskList;
        try{
            taskList=userService.getAllTaskByUserId(userId);
        }catch(UserNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(taskList,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteUser/{userId}")
    @CacheEvict(value = "cacheInfo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteUser(@PathVariable("userId") int userId) throws UserNotFoundException {

        UserResponseDto userResponseDto;
        try {
            userResponseDto = userService.deleteUser(userId);
        }catch(UserNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userResponseDto,HttpStatus.ACCEPTED);
    }
}
