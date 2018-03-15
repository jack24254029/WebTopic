package com.shunminchang.repository;

import com.shunminchang.model.NurseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public interface NurseRepository extends JpaRepository<NurseEntity, String> {

    @Modifying
    @Transactional
    @Query("UPDATE NurseEntity nurse SET nurse.name=:qName, nurse.updateTime=:qUpdateTime WHERE nurse.id=:qId")
    void updateUser(@Param("qName") String name, @Param("qUpdateTime") Timestamp updateTime, @Param("qId") String id);
}