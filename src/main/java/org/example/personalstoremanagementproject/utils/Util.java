package org.example.personalstoremanagementproject.utils;

import org.example.personalstoremanagementproject.repositories.BaseRepository;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
    public<T> String createId(T entity, BaseRepository<T, ?> repository) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        long count = repository.count();
        String formattedCount = String.format("%03d", count + 1);
        String prefix = entity.getClass().getSimpleName().substring(0, 1).toUpperCase();
        return prefix + date + formattedCount;
    }

}
