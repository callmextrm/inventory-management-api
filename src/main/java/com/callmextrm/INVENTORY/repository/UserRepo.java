package com.callmextrm.INVENTORY.repository;

import com.callmextrm.INVENTORY.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
   Users findByUsername(String username);
   Boolean existsByUsername(String username);
}
