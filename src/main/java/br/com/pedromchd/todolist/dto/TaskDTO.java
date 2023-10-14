package br.com.pedromchd.todolist.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.pedromchd.todolist.model.Task.Priority;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TaskDTO(
        Integer user_id,
        String title,
        String description,
        LocalDateTime startAt,
        LocalDateTime endAt,
        Priority priority) {

}
