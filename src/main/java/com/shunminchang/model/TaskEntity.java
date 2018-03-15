package com.shunminchang.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Task", schema = "WebTopic", catalog = "")
@IdClass(TaskEntityPK.class)
public class TaskEntity {
    private int siteId;
    private String nurseId;
    private Timestamp createTime;

    @Id
    @Column(name = "site_id", nullable = false)
    @ManyToMany
    @JoinColumn(name = "SITE_ID", table = "Site")
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Id
    @Column(name = "nurse_id", nullable = false, length = 10)
    @ManyToMany
    @JoinColumn(name = "NURSE_ID", table = "Nurse")
    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return siteId == that.siteId &&
                Objects.equals(nurseId, that.nurseId) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(siteId, nurseId, createTime);
    }
}
