package com.shunminchang.repository;

import com.shunminchang.model.SiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface SiteRepository extends JpaRepository<SiteEntity, Integer> {


    @Modifying
    @Transactional
    @Query("UPDATE SiteEntity site SET site.name=:qName, site.name=:qName, site.updateTime=:qUpdateTime WHERE site.id=:qId")
    public void updateSite(@Param("qName") String name,@Param("qUpdateTime") Timestamp updateTime,
                           @Param("qId") Integer id);
}