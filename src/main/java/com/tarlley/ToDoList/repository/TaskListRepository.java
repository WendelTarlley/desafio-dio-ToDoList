package com.tarlley.ToDoList.repository;

import com.tarlley.ToDoList.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Integer> {

    List<TaskList> findByUserId(Integer userId);
}
