package com.task_manager.demo.repository;

import com.opencsv.bean.CsvToBeanBuilder;
import com.task_manager.demo.contantes.FileData;
import com.task_manager.demo.models.TaskDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Composant de lecture du csv
 */
@Component
@AllArgsConstructor
public class CsvReader {

    private final FileData fileData;

    public List<TaskDTO> readCsv() throws IOException {
        Reader reader = new FileReader(fileData.getClassPathResource().getFile());
        return new CsvToBeanBuilder<TaskDTO>(reader).withType(TaskDTO.class).build().parse();
    }
}
