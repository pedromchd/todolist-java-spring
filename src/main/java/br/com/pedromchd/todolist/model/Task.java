package br.com.pedromchd.todolist.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "task")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Task {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("tasks")
    private User user;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 255, nullable = true)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = true)
    private LocalDateTime endAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    @JsonIgnore
    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

}
