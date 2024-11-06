package project.personal.personalstoremanagementproject.utils;

import project.personal.personalstoremanagementproject.repositories.BaseRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  StringUtil class
 */
public class StringUtil {

    /**
     * Generates unique id for entities
     * @param entity
     * @param repository
     * @return
     * @param <T>
     */
    public<T> String createId(T entity, BaseRepository<T, ?> repository) {
        // Get current date
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        // Get count of entities
        long count = repository.count();
        // Format count to 3 digits
        String formattedCount = String.format("%03d", count + 1);
        // Get first letter of entity class name
        String prefix = entity.getClass().getSimpleName().substring(0, 1).toUpperCase();
        // Return generated id
        return prefix + date + formattedCount;
    }

}
