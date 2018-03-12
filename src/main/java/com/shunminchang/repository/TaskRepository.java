package com.shunminchang.repository;

import com.shunminchang.model.TaskEntity;
import com.shunminchang.model.TaskEntityPK;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, TaskEntityPK> {

    @Query(value = "SELECT t FROM TaskEntity  t WHERE t.siteId = :siteId")
    List<TaskEntity> findAllBySiteId(@Param("siteId") Integer siteId);

    @Query(value = "SELECT t FROM TaskEntity  t WHERE t.nurseId = :nurseId")
    List<TaskEntity> findAllByNurseId(@Param("nurseId") String nurseId);

    @Query(value = "SELECT t FROM TaskEntity  t WHERE t.nurseId <> :nurseId")
    List<TaskEntity> findAllByNurseIdNotQuery(@Param("nurseId") String nurseId);
}
