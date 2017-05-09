package com.waterapp.domain.system;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tb_systems")
public class Systems {
    @Id
    private String id;
    private String deviceCode;
    private Integer errorCode;
    private Integer mode;
    private BigDecimal pressure;
    private BigDecimal changeTime;
    private BigDecimal sleepFreq;
    private BigDecimal sleepDelay;
    private BigDecimal aweakShift;
    private BigDecimal lackLevel;
    private BigDecimal lackPressure;
    private Integer reset;
    private Integer allowControl;
    private BigDecimal voltagea;
    private BigDecimal voltageb;
    private BigDecimal voltagec;
    private BigDecimal currenta;
    private BigDecimal currentb;
    private BigDecimal currentc;
    private BigDecimal powera;
    private BigDecimal powerb;
    private BigDecimal powerc;
    private BigDecimal eConsumption;

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

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public void setPressure(BigDecimal pressure) {
        this.pressure = pressure;
    }

    public BigDecimal getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(BigDecimal changeTime) {
        this.changeTime = changeTime;
    }

    public BigDecimal getSleepFreq() {
        return sleepFreq;
    }

    public void setSleepFreq(BigDecimal sleepFreq) {
        this.sleepFreq = sleepFreq;
    }

    public BigDecimal getSleepDelay() {
        return sleepDelay;
    }

    public void setSleepDelay(BigDecimal sleepDelay) {
        this.sleepDelay = sleepDelay;
    }

    public BigDecimal getAweakShift() {
        return aweakShift;
    }

    public void setAweakShift(BigDecimal aweakShift) {
        this.aweakShift = aweakShift;
    }

    public BigDecimal getLackLevel() {
        return lackLevel;
    }

    public void setLackLevel(BigDecimal lackLevel) {
        this.lackLevel = lackLevel;
    }

    public BigDecimal getLackPressure() {
        return lackPressure;
    }

    public void setLackPressure(BigDecimal lackPressure) {
        this.lackPressure = lackPressure;
    }

    public Integer getReset() {
        return reset;
    }

    public void setReset(Integer reset) {
        this.reset = reset;
    }

    public Integer getAllowControl() {
        return allowControl;
    }

    public void setAllowControl(Integer allowControl) {
        this.allowControl = allowControl;
    }

    public BigDecimal getVoltagea() {
        return voltagea;
    }

    public void setVoltagea(BigDecimal voltagea) {
        this.voltagea = voltagea;
    }

    public BigDecimal getVoltageb() {
        return voltageb;
    }

    public void setVoltageb(BigDecimal voltageb) {
        this.voltageb = voltageb;
    }

    public BigDecimal getVoltagec() {
        return voltagec;
    }

    public void setVoltagec(BigDecimal voltagec) {
        this.voltagec = voltagec;
    }

    public BigDecimal getCurrenta() {
        return currenta;
    }

    public void setCurrenta(BigDecimal currenta) {
        this.currenta = currenta;
    }

    public BigDecimal getCurrentb() {
        return currentb;
    }

    public void setCurrentb(BigDecimal currentb) {
        this.currentb = currentb;
    }

    public BigDecimal getCurrentc() {
        return currentc;
    }

    public void setCurrentc(BigDecimal currentc) {
        this.currentc = currentc;
    }

    public BigDecimal getPowera() {
        return powera;
    }

    public void setPowera(BigDecimal powera) {
        this.powera = powera;
    }

    public BigDecimal getPowerb() {
        return powerb;
    }

    public void setPowerb(BigDecimal powerb) {
        this.powerb = powerb;
    }

    public BigDecimal getPowerc() {
        return powerc;
    }

    public void setPowerc(BigDecimal powerc) {
        this.powerc = powerc;
    }

    public BigDecimal geteConsumption() {
        return eConsumption;
    }

    public void seteConsumption(BigDecimal eConsumption) {
        this.eConsumption = eConsumption;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}