package com.task_manager.demo.contantes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.ClassPathResource;

/**
 * Enumerateur pour la lecture du fichier selon lq configuration spring
 * DATA_CSV pour la production
 * DATA_TEST_CSB pour les tests
 */
@AllArgsConstructor
@Getter
public enum FileData {
    DATA_CSV(new ClassPathResource("data.csv")),
    DATA_TEST_CSV(new ClassPathResource("dataTest.csv"));
    ClassPathResource classPathResource;
}
