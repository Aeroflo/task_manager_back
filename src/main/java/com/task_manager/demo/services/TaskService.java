package com.task_manager.demo.services;

import com.task_manager.demo.contantes.TaskStatus;
import com.task_manager.demo.repository.TaskDataRepository;
import lombok.AllArgsConstructor;
import com.task_manager.demo.models.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service de traitement des task
 */
@Service
@AllArgsConstructor
public class TaskService {

    private final TaskDataRepository taskDataRepository;
    private final TaskIdGeneratorService taskIdGeneratorService;

    public List<TaskDTO> getAllTasks() throws Exception {
        return taskDataRepository.getAllTasks() ;
    }

    public TaskDTO getTaskById(String taskId) throws Exception{
        return taskDataRepository.getTaskById(taskId);
    }

    public List<TaskDTO> getTaskByStatus(TaskStatus taskStatus) throws Exception {
        return taskDataRepository.getTaskByStatus(taskStatus);
    }

    public TaskDTO createTask(TaskDTO taskDTO) throws Exception {
        taskDTO.setId(taskIdGeneratorService.generateTaskId());
        taskDataRepository.createTask(taskDTO);
        return taskDTO;
    }

    public TaskDTO updateTask(String id, TaskDTO taskDTO) throws Exception {
        return taskDataRepository.updateTask(id, taskDTO);
    }

    public void deleteTask(String id) throws Exception {
        taskDataRepository.deleteTask(id);
    }

}
