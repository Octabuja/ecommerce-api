package com.octavio.ecommerce_api.repository;

import com.octavio.ecommerce_api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository
        extends JpaRepository<Role, Long> {

}