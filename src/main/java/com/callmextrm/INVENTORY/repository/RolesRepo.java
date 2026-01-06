package com.callmextrm.INVENTORY.repository;

import com.callmextrm.INVENTORY.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepo extends JpaRepository<Role,Long> {
   Optional<Role> findByRolename(String rolename);
   Boolean existsByRolename(String rolename);
}

