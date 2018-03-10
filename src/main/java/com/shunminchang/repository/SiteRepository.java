package com.shunminchang.repository;

import com.shunminchang.model.SiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public interface SiteRepository extends JpaRepository<SiteEntity, Integer> {

    @Modifying
    @Transactional
    @Query("update SiteEntity site set site.name=:qName, site.name=:qName, site.updateTime=:qUpdateTime where site.id=:qId")
    public void updateSite(@Param("qName") String name,@Param("qUpdateTime") Timestamp updateTime,
                           @Param("qId") Integer id);
}