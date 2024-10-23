package com.task_manager.demo.repository;

import com.task_manager.demo.contantes.TaskStatus;
import com.task_manager.demo.exceptions.TaskNotFoundException;
import lombok.AllArgsConstructor;
import com.task_manager.demo.models.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Task repository
 * S'occupe de la recuperation ou de la suavegarde de tache par lecture/ecriture du CSV
 */
@Service
@AllArgsConstructor
public class TaskDataRepository {
    private final CsvReader csvReader;
    private final CsvWriter csvWriter;

    public List<TaskDTO> getAllTasks() throws Exception {
        return csvReader.readCsv();
    }

    public TaskDTO getTaskById(String id) throws Exception {
        List<TaskDTO> data = csvReader.readCsv();
        return data.stream().filter(d -> d.getId().equals(id)).findFirst().orElseThrow(TaskNotFoundException::new);
    }

    public void createTask(TaskDTO taskDTO) throws Exception {
        List<TaskDTO> data = csvReader.readCsv();
        data.add(taskDTO);
        csvWriter.writeCsv(data);
    }

    public TaskDTO updateTask(String id, TaskDTO taskDTO) throws Exception {
        List<TaskDTO> data = csvReader.readCsv();
        TaskDTO existingTask = data.stream().filter(d -> id.equals(d.getId())).findFirst().orElseThrow(TaskNotFoundException::new);
        existingTask.setLabel(taskDTO.getLabel());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setTaskStatus(taskDTO.getTaskStatus());
        csvWriter.writeCsv(data);
        return existingTask;
    }

    public List<TaskDTO> getTaskByStatus(TaskStatus taskStatus) throws Exception {
        List<TaskDTO> data = csvReader.readCsv();
        return data.stream().filter(d -> taskStatus.equals(d.getTaskStatus())).toList();
    }

    public void deleteTask(String taskId) throws Exception {
        List<TaskDTO> data = csvReader.readCsv();
        data = data.stream().filter(d -> !taskId.equals(d.getId())).toList();
        csvWriter.writeCsv(data);
    }

    public void cleanCsvFile() throws Exception {
        csvWriter.writeCsv(new ArrayList<>());
    }
}
