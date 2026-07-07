package com.tms.repository;

import com.tms.model.Status;
import com.tms.model.TaskEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Modifying(clearAutomatically = true) // Иначе старая сущность хранится в persistence context и в контроллере возвращается старый статус
    @Transactional
    @Query("UPDATE TaskEntity t SET t.status = :status WHERE t.id = :id")
    int updateStatusById(@Param("id") Long id, @Param("status") Status status);
}
