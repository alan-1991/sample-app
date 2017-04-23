package com.alan.sample.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * VARCHAR(30) 必填<br>
     * 主键
     */
    private String id;

    /**
     * VARCHAR(30)<br>
     * 订单名称
     */
    private String name;

    /**
     * INTEGER(10)<br>
     * 状态
     */
    private Integer status;

    /**
     * DECIMAL(9,2)<br>
     * 金额
     */
    private BigDecimal amount;

    /**
     * VARCHAR(30) 必填<br>
     * 获得 主键
     */
    public String getId() {
        return id;
    }

    /**
     * VARCHAR(30) 必填<br>
     * 设置 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * VARCHAR(30)<br>
     * 获得 订单名称
     */
    public String getName() {
        return name;
    }

    /**
     * VARCHAR(30)<br>
     * 设置 订单名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * INTEGER(10)<br>
     * 获得 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * INTEGER(10)<br>
     * 设置 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * DECIMAL(9,2)<br>
     * 获得 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * DECIMAL(9,2)<br>
     * 设置 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", amount=").append(amount);
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
        OrderInfo other = (OrderInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        return result;
    }
}