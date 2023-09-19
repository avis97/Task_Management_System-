package com.JavaTask.TaskManageMent.Repository;

import com.JavaTask.TaskManageMent.Entitys.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>{

}
