package com.JavaTask.TaskManageMent.Converter;

import com.JavaTask.TaskManageMent.Entitys.Task;
import com.JavaTask.TaskManageMent.RequestDto.TaskRequestDto;
import com.JavaTask.TaskManageMent.ResponseDto.TaskResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TaskConverter{

    public static Task TaskRequestDtoToTask(TaskRequestDto requestDto){
        return Task.builder()
                .taskName(requestDto.getTaskName())
                .category(requestDto.getCategories())
                .aboutTask(requestDto.getAboutTask())
                .dueDate(requestDto.getDueDate())
                .build();
    }
    public static TaskResponseDto TaskToTaskResponseDto(Task task){
        return TaskResponseDto.builder()
                .aboutTask(task.getAboutTask())
                .category(task.getCategory())
                .dueDate(task.getDueDate())
                .taskName(task.getTaskName())
                .fullName(task.getUser().getFullName())
                .build();
    }
}
