package com.JavaTask.TaskManageMent.Service.impl;

import com.JavaTask.TaskManageMent.Converter.TaskConverter;
import com.JavaTask.TaskManageMent.Entitys.Task;
import com.JavaTask.TaskManageMent.Entitys.User;
import com.JavaTask.TaskManageMent.Exception.TaskNotFoundException;
import com.JavaTask.TaskManageMent.Exception.UserNotFoundException;
import com.JavaTask.TaskManageMent.Repository.TaskRepository;
import com.JavaTask.TaskManageMent.Repository.UserRepository;
import com.JavaTask.TaskManageMent.RequestDto.TaskRequestDto;
import com.JavaTask.TaskManageMent.ResponseDto.TaskResponseDto;
import com.JavaTask.TaskManageMent.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) throws UserNotFoundException {
        User user;
        try{
            user=userRepository.findById(taskRequestDto.getUserId()).get();
        }catch(Exception e){
            throw new UserNotFoundException("User Id Not Present In Our System");
        }

        Task task= TaskConverter.TaskRequestDtoToTask(taskRequestDto);
        task.setUser(user);

        //save in task repository.
        taskRepository.save(task);

        //convert dto to responseDto
        TaskResponseDto taskResponseDto=TaskConverter.TaskToTaskResponseDto(task);
        taskResponseDto.setFullName(user.getFullName());

        return taskResponseDto;
    }
    public List<TaskResponseDto> allTask(){
        List<Task> tasks=taskRepository.findAll();
        List<TaskResponseDto> listOfTask=new ArrayList<>();
        for(Task task:tasks){
            TaskResponseDto taskResponseDto=TaskConverter.TaskToTaskResponseDto(task);
            listOfTask.add(taskResponseDto);
        }
        return listOfTask;
    }
    public TaskResponseDto getTaskById(int taskId) throws TaskNotFoundException {
        Task task;
        try{
            task=taskRepository.findById(taskId).get();
        }catch(Exception e){
            throw new TaskNotFoundException("Task Are Not Found");
        }
        TaskResponseDto responseDto=TaskConverter.TaskToTaskResponseDto(task);
        return responseDto;
    }
}
