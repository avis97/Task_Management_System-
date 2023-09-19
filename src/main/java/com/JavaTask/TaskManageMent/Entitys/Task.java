package com.JavaTask.TaskManageMent.Entitys;
import com.JavaTask.TaskManageMent.Enum.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TaskCol")
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private String taskName;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String aboutTask;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @ManyToOne
    @JoinColumn
    private User user;
}
