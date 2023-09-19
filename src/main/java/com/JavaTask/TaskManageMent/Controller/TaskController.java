package com.JavaTask.TaskManageMent.Controller;

import com.JavaTask.TaskManageMent.Exception.TaskNotFoundException;
import com.JavaTask.TaskManageMent.Exception.UserNotFoundException;
import com.JavaTask.TaskManageMent.RequestDto.TaskRequestDto;
import com.JavaTask.TaskManageMent.ResponseDto.TaskResponseDto;
import com.JavaTask.TaskManageMent.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/task")
@EnableCaching
public class TaskController{
    @Autowired
    TaskService taskService;

    @PostMapping("/addtask")
    public ResponseEntity addTask(@RequestBody TaskRequestDto taskRequestDto) throws UserNotFoundException {
        TaskResponseDto responseDto=taskService.addTask(taskRequestDto);
        return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
    }
    @GetMapping("/getAllTask")
    @Cacheable(value = "CatchInfo")
    public ResponseEntity allTask(){
        List<TaskResponseDto> taskList;
        try{
            taskList=taskService.allTask();
        }catch(Exception e){
            return new ResponseEntity("Dont Have any Task",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(taskList,HttpStatus.ACCEPTED);
    }
    @GetMapping("/getTaskById/{taskId}")
    @Cacheable(value = "CatchInfo")
    public ResponseEntity getTaskById(@PathVariable("taskId") int taskId){
        TaskResponseDto taskResponseDto;
        try{
            taskResponseDto=taskService.getTaskById(taskId);
        }catch(TaskNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(taskResponseDto,HttpStatus.ACCEPTED);
    }
}
