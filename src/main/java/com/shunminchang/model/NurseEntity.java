package com.shunminchang.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Nurse", schema = "WebTopic", catalog = "")
public class NurseEntity {
    private int id;
    private String uid;
    private String name;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Collection<TaskEntity> nurseByTaskId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uid", nullable = false, length = 10, columnDefinition = "NOT NULL")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 10, columnDefinition = "NOT NULL")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    @Value(value = "CURRENT_TIMESTAMP")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NurseEntity that = (NurseEntity) o;
        return id == that.id &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(name, that.name) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, uid, name, createTime, updateTime);
    }

    @OneToMany(mappedBy = "taskByNurseId")
    public Collection<TaskEntity> getNurseByTaskId() {
        return nurseByTaskId;
    }

    public void setNurseByTaskId(Collection<TaskEntity> nurseByTaskId) {
        this.nurseByTaskId = nurseByTaskId;
    }
}
