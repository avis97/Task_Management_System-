package com.JavaTask.TaskManageMent.ResponseDto;

import com.JavaTask.TaskManageMent.Enum.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDto{
    private String fullName;
    private String taskName;
    private Category category;
    private String aboutTask;
    private Date dueDate;

}
