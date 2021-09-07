package com.oleg.warehouse.repository;

import com.oleg.warehouse.entity.UserEntity;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByLogin(String login);

    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findByLoginAndPassword(@NotNull String login, @NotNull String password);
}
