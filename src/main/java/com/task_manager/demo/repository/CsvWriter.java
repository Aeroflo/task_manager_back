package com.task_manager.demo.repository;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.task_manager.demo.contantes.FileData;
import com.task_manager.demo.models.TaskDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

/**
 * Composant d'ecriture du CSV
 */
@Component
@AllArgsConstructor
public class CsvWriter {

    private final FileData fileData;

    public void writeCsv(List<TaskDTO> data) throws Exception {
        Writer writer = new FileWriter(fileData.getClassPathResource().getFile());
        StatefulBeanToCsv<TaskDTO> toCsv = new StatefulBeanToCsvBuilder<TaskDTO>(writer).build();
        toCsv.write(data);
        writer.close();
    }
}
