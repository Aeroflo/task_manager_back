package com.task_manager.demo;

import com.task_manager.demo.contantes.TaskStatus;
import com.task_manager.demo.models.TaskDTO;
import com.task_manager.demo.repository.TaskDataRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AbstractTI {

    @Autowired
    protected TaskDataRepository taskDataRepository;

    @Autowired
    protected MockMvc mockMvc;

    //DATA MOCK
    private TaskDTO taskTodo = new TaskDTO("idTodo", "Test Todo", "Description todo", TaskStatus.TODO);
    private TaskDTO taskProgressing = new TaskDTO("idProgress", "Test Progress", "Description Progress", TaskStatus.IN_PROGRESS);
    private TaskDTO taskDone = new TaskDTO("idDone", "Test Done", "Description done", TaskStatus.DONE);

    @BeforeEach
    @SneakyThrows
    void setUpData(){
        taskDataRepository.cleanCsvFile();
        taskDataRepository.createTask(taskTodo);
        taskDataRepository.createTask(taskProgressing);
        taskDataRepository.createTask(taskDone);
    }
}
