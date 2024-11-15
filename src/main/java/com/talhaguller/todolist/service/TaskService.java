package com.talhaguller.todolist.service;

import com.talhaguller.todolist.dto.TaskRequest;
import com.talhaguller.todolist.dto.TaskResponse;
import com.talhaguller.todolist.entity.Task;
import com.talhaguller.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskResponse addTask(TaskRequest taskRequest){
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(taskRequest.isCompleted());
        task.setCreatedAt(LocalDateTime.now());

        Task savedTask= taskRepository.save(task);
        return mapToTaskResponse(savedTask);
    }

    public List<TaskResponse> getAllTask(){
        return taskRepository.findAll().stream()
                .map(this::mapToTaskResponse)
                .collect(Collectors.toList());
    }



    private TaskResponse mapToTaskResponse(Task task){
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setCompleted(task.isCompleted());
        taskResponse.setCreatedAt(task.getCreatedAt());
        return taskResponse;
    }
}
