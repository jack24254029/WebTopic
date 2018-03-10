package com.shunminchang.repository;

import com.shunminchang.model.NurseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface NurseRepository extends JpaRepository<NurseEntity, Integer> {

    @Modifying
    @Transactional
    @Query("update NurseEntity nurse set nurse.uid=:qUid, nurse.name=:qName, nurse.updateTime=:qUpdateTime where nurse.id=:qId")
    public void updateUser(@Param("qUid") String uid, @Param("qName") String name,
                           @Param("qUpdateTime") Timestamp updateTime, @Param("qId") Integer id);
}