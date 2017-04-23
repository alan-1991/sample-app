package com.alan.sample.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class GradeInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * INTEGER(10) 必填<br>
     * 
     */
    private Integer id;

    /**
     * INTEGER(10)<br>
     * 
     */
    private Integer studentId;

    /**
     * INTEGER(10)<br>
     * 
     */
    private Integer subjectId;

    /**
     * DECIMAL(3,1)<br>
     * 
     */
    private BigDecimal grade;

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
     * INTEGER(10)<br>
     * 获得 
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * INTEGER(10)<br>
     * 设置 
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * INTEGER(10)<br>
     * 获得 
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * INTEGER(10)<br>
     * 设置 
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * DECIMAL(3,1)<br>
     * 获得 
     */
    public BigDecimal getGrade() {
        return grade;
    }

    /**
     * DECIMAL(3,1)<br>
     * 设置 
     */
    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", studentId=").append(studentId);
        sb.append(", subjectId=").append(subjectId);
        sb.append(", grade=").append(grade);
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
        GradeInfo other = (GradeInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getSubjectId() == null ? other.getSubjectId() == null : this.getSubjectId().equals(other.getSubjectId()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getSubjectId() == null) ? 0 : getSubjectId().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        return result;
    }
}