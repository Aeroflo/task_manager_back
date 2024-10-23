package com.task_manager.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task_manager.demo.contantes.TaskStatus;
import com.task_manager.demo.models.TaskDTO;
import lombok.SneakyThrows;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TaskControllerIntegrationTest extends AbstractTI {


    @Test
    @SneakyThrows
    void getAllTasks(){
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(3)));
    }

    @Test
    @SneakyThrows
    void getAllTodoTasks(){
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks").queryParam("taskStatus", TaskStatus.TODO.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(jsonPath("$[?(@.label == \"Test Todo\")]").exists());
    }

    @Test
    @SneakyThrows
    void getAllProgressTasks(){
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks").queryParam("taskStatus", TaskStatus.IN_PROGRESS.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(jsonPath("$[?(@.label == \"Test Progress\")]").exists());
    }

    @Test
    @SneakyThrows
    void getAllDoneTasks(){
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks").queryParam("taskStatus", TaskStatus.DONE.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(jsonPath("$[?(@.label == \"Test Done\")]").exists());
    }

    @Test
    @SneakyThrows
    void createTasks(){
        //GIVEN
        String createTaskJson = transformToJson(new TaskDTO(null, "new task", "description", TaskStatus.TODO));

        //WHEN THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/tasks").contentType(MediaType.APPLICATION_JSON).content(createTaskJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[?(@.label == \"new task\")]").exists());
    }

    @Test
    @SneakyThrows
    void updateTasks(){
        //GIVEN
        String updateTaskJson = transformToJson(new TaskDTO("idTodo", "updatedTask", "description", TaskStatus.TODO));

        //WHEN THEN
        mockMvc.perform(MockMvcRequestBuilders.put("/tasks/{id}", "idTodo")
                        .contentType(MediaType.APPLICATION_JSON).content(updateTaskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.label == \"updatedTask\")]").exists());
    }

    @Test
    @SneakyThrows
    void getTaskById(){
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/{id}", "idTodo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.label == \"Test Todo\")]").exists());
    }

    @Test
    @SneakyThrows
    void checkErrorValidation(){
        //GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        TaskDTO createTask = new TaskDTO(null, "new task", "", TaskStatus.TODO);
        String json = objectMapper.writeValueAsString(createTask);

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void getTaskByIdNotFound(){
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/{id}", "notFound"))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void deleteTask(){
        mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/{id}", "notFound"))
                .andExpect(status().isOk());
    }
    

    @SneakyThrows
    private String transformToJson(TaskDTO taskDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(taskDTO);

    }



}
