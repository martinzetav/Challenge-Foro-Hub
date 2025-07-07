package com.app.foro_hub.repository;

import com.app.foro_hub.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByTitleAndMessageIgnoreCase(String title, String message);
}
