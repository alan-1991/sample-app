package com.alan.sample.model;

import java.io.Serializable;

public class TestChild implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * INTEGER(10) 必填<br>
     * 
     */
    private Integer id;

    /**
     * VARCHAR(30)<br>
     * 
     */
    private String vlaue;

    /**
     * INTEGER(10) 必填<br>
     * 获得 
     */
    public Integer getId() {
        return id;
    }

    /**
     * INTEGER(10) 必填<br>
     * 设置 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * VARCHAR(30)<br>
     * 获得 
     */
    public String getVlaue() {
        return vlaue;
    }

    /**
     * VARCHAR(30)<br>
     * 设置 
     */
    public void setVlaue(String vlaue) {
        this.vlaue = vlaue == null ? null : vlaue.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", vlaue=").append(vlaue);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TestChild other = (TestChild) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVlaue() == null ? other.getVlaue() == null : this.getVlaue().equals(other.getVlaue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVlaue() == null) ? 0 : getVlaue().hashCode());
        return result;
    }
}