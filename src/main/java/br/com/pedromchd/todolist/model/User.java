package br.com.pedromchd.todolist.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    private String fullname;

    @JsonIgnore
    @Column(length = 255, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Task> tasks;

    @JsonIgnore
    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

}
