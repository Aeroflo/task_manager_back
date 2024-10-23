package com.task_manager.demo.models;

import com.task_manager.demo.contantes.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    public String id;

    @NotBlank(message = "Le titre doit etre present")
    @Size(max = 50, message = "le titre doit faire 50 charateres max")
    public String label;

    @NotBlank(message = "La description doit etre presente")
    @Size(max = 300, message = "la description doit faire 300 charateres max")
    public String description;

    @NotNull(message = "Une tache doit avoir un status")
    public TaskStatus taskStatus;

}
