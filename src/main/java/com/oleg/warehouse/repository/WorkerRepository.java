package com.oleg.warehouse.repository;

import com.oleg.warehouse.entity.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<WorkerEntity, Long> {
}
