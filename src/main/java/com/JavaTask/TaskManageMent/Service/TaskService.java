package com.JavaTask.TaskManageMent.Service;

import com.JavaTask.TaskManageMent.Exception.TaskNotFoundException;
import com.JavaTask.TaskManageMent.Exception.UserNotFoundException;
import com.JavaTask.TaskManageMent.RequestDto.TaskRequestDto;
import com.JavaTask.TaskManageMent.ResponseDto.TaskResponseDto;

import java.util.List;

public interface TaskService{
    TaskResponseDto addTask(TaskRequestDto taskRequestDto) throws UserNotFoundException;
    List<TaskResponseDto> allTask();
    TaskResponseDto getTaskById(int taskId) throws TaskNotFoundException;
}
