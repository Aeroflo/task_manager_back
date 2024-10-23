package com.task_manager.demo;

import com.task_manager.demo.contantes.FileData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FileDataConfiguration {
    @Bean
    @Profile("!test")
    public FileData fileData(){
        return FileData.DATA_CSV;
    }

    @Bean
    @Profile("test")
    public FileData fileTestData(){
        return FileData.DATA_TEST_CSV;
    }


}
