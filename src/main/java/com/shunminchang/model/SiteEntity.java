package com.shunminchang.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Site", schema = "WebTopic", catalog = "")
public class SiteEntity {
    private int id;
    private String name;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Collection<TaskEntity> siteByTaskId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
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
        SiteEntity that = (SiteEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, createTime, updateTime);
    }

    @OneToMany(mappedBy = "taskBySiteId")
    public Collection<TaskEntity> getSiteByTaskId() {
        return siteByTaskId;
    }

    public void setSiteByTaskId(Collection<TaskEntity> siteByTaskId) {
        this.siteByTaskId = siteByTaskId;
    }
}
