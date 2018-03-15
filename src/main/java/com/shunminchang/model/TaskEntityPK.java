package com.shunminchang.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TaskEntityPK implements Serializable {
    private int siteId;
    private String nurseId;

    @Column(name = "site_id", nullable = false)
    @Id
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Column(name = "nurse_id", nullable = false, length = 10)
    @Id
    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntityPK that = (TaskEntityPK) o;
        return siteId == that.siteId &&
                Objects.equals(nurseId, that.nurseId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(siteId, nurseId);
    }
}
