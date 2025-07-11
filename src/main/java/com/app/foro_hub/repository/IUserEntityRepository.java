package com.app.foro_hub.repository;

import com.app.foro_hub.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByEmail(String email);
}
