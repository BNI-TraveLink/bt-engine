package com.btengine.btlink.repository;

import com.btengine.btlink.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, String> {
}
