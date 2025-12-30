package com.callmextrm.INVENTORY.repository;

import com.callmextrm.INVENTORY.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends JpaRepository<Role,Long> {
   Role findByRolename(String username);
   Boolean existsByRolename(String username);
}

