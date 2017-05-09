package com.waterapp.domain.transducer;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tb_transducer4")
public class Transducer4 {
    @Id
    private String id;
    private String deviceCode;
    private Integer mode;
    private BigDecimal inverterVoltage;
    private BigDecimal inverterCurrent;
    private Integer speed;
    private BigDecimal freq;
    private Integer errorCode;
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

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public BigDecimal getInverterVoltage() {
        return inverterVoltage;
    }

    public void setInverterVoltage(BigDecimal inverterVoltage) {
        this.inverterVoltage = inverterVoltage;
    }

    public BigDecimal getInverterCurrent() {
        return inverterCurrent;
    }

    public void setInverterCurrent(BigDecimal inverterCurrent) {
        this.inverterCurrent = inverterCurrent;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public BigDecimal getFreq() {
        return freq;
    }

    public void setFreq(BigDecimal freq) {
        this.freq = freq;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
