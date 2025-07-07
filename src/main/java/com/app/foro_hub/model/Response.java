package com.app.foro_hub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "responses")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
    private String solution;
}
