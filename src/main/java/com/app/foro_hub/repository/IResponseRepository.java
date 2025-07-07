package com.app.foro_hub.repository;

import com.app.foro_hub.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResponseRepository extends JpaRepository<Response, Long> {
}
