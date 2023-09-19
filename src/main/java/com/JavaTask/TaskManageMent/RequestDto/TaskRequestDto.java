package com.JavaTask.TaskManageMent.RequestDto;

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
public class TaskRequestDto{
    private int userId;
    private String taskName;
    private Category categories;
    private String aboutTask;
    private Date dueDate;
}
