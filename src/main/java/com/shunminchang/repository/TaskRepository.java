package com.shunminchang.repository;

import com.shunminchang.model.TaskEntity;
import com.shunminchang.model.TaskEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, TaskEntityPK> {

    List<TaskEntity> findAllBySiteId(@Param("siteId") Integer siteId);

    List<TaskEntity> findAllByNurseId(@Param("nurseId") String nurseId);

    @Query(value = "SELECT t FROM TaskEntity  t WHERE t.nurseId <> :nurseId")
    List<TaskEntity> findAllByNurseIdNotQuery(@Param("nurseId") String nurseId);

    TaskEntity findAllByNurseIdAndSiteId(@Param("nurseId") String nurseId, @Param("siteId") int siteId);
}
