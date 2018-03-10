package com.shunminchang.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Task", schema = "WebTopic", catalog = "")
public class TaskEntity {
    private int id;
    private Timestamp createTime;
    private NurseEntity taskByNurseId;
    private SiteEntity taskBySiteId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id == that.id &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createTime);
    }

    @ManyToOne
    @JoinColumn(name = "nurse_id", referencedColumnName = "id", nullable = false)
    public NurseEntity getTaskByNurseId() {
        return taskByNurseId;
    }

    public void setTaskByNurseId(NurseEntity taskByNurseId) {
        this.taskByNurseId = taskByNurseId;
    }

    @ManyToOne
    @JoinColumn(name = "site_id", referencedColumnName = "id", nullable = false)
    public SiteEntity getTaskBySiteId() {
        return taskBySiteId;
    }

    public void setTaskBySiteId(SiteEntity taskBySiteId) {
        this.taskBySiteId = taskBySiteId;
    }
}
