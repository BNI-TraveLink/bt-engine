package com.btengine.btlink.repository;

import com.btengine.btlink.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, String> {
    @Query("SELECT l FROM Login l WHERE l.userId = :userId AND l.mpin = :mpin")
    Optional<Login> findUserByCredentials(@Param("userId") String userId, @Param("mpin") String mpin);

    @Query("SELECT l FROM Login l WHERE l.userId = :userId")
    Optional<Login> findUserByUserId(@Param("userId") String userId);
}
