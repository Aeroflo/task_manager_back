package com.task_manager.demo.controllers.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Model de retour de l'API en erreur
 */
@Getter
@Setter
@AllArgsConstructor
public class ApiError {
    int statusCode;
    String statusName;
    List<String> error = new ArrayList<>();
}
