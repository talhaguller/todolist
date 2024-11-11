package com.talhaguller.todolist.repository;

import com.talhaguller.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByUserId(Long userId);

    List<Task> findByIsCompleted(boolean isCompleted);
}
