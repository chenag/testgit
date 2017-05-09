package com.waterapp.domain.box;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tb_box")
public class Box {
    @Id
    private String id;
    private String deviceCode;
    private BigDecimal vavle;
    private BigDecimal level;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public BigDecimal getVavle() {
        return vavle;
    }

    public void setVavle(BigDecimal vavle) {
        this.vavle = vavle;
    }

    public BigDecimal getLevel() {
        return level;
    }

    public void setLevel(BigDecimal level) {
        this.level = level;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
