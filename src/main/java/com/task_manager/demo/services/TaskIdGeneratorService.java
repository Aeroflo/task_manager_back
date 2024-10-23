package com.task_manager.demo.services;

import com.fasterxml.uuid.Generators;
import org.springframework.stereotype.Service;

/**
 * Generation d'identifiant task
 */
@Service
public class TaskIdGeneratorService {

    public String generateTaskId(){
        return Generators.timeBasedEpochGenerator().generate().toString();
    }
}
