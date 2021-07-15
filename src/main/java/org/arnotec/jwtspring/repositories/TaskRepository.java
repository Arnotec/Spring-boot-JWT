package org.arnotec.jwtspring.repositories;

import org.arnotec.jwtspring.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
