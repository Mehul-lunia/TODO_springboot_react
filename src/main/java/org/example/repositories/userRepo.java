package org.example.repositories;

import org.example.model.TODO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<TODO,Long> {}
