package org.arnotec.jwtspring.repositories;

import org.arnotec.jwtspring.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username) ;
}
