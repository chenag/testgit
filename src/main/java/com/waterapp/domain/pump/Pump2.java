package com.waterapp.domain.pump;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name="tb_pump2")
public class Pump2 {

    @Id
    private String id;
    private String deviceCode;
    private BigDecimal voltagea;
    private BigDecimal voltageb;
    private BigDecimal voltagec;
    private BigDecimal currenta;
    private BigDecimal currentb;
    private BigDecimal currentc;
    private BigDecimal powera;
    private BigDecimal powerb;
    private BigDecimal powerc;
    private BigDecimal freq;
    private Integer manual;
    private Integer mode;
    private BigDecimal tempUp;
    private BigDecimal tempDown;
    private BigDecimal coilTemp;
    private BigDecimal oilTemp;
    private BigDecimal bodyTemp;
    private BigDecimal inverterVoltage;
    private BigDecimal inverterCurrent;
    private Integer speed;
    private Integer errorCode;
    private BigDecimal constantTime;
    private BigDecimal inverterTime;
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

    public BigDecimal getFreq() {
        return freq;
    }

    public void setFreq(BigDecimal freq) {
        this.freq = freq;
    }

    public Integer getManual() {
        return manual;
    }

    public void setManual(Integer manual) {
        this.manual = manual;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public BigDecimal getTempUp() {
        return tempUp;
    }

    public void setTempUp(BigDecimal tempUp) {
        this.tempUp = tempUp;
    }

    public BigDecimal getTempDown() {
        return tempDown;
    }

    public void setTempDown(BigDecimal tempDown) {
        this.tempDown = tempDown;
    }

    public BigDecimal getCoilTemp() {
        return coilTemp;
    }

    public void setCoilTemp(BigDecimal coilTemp) {
        this.coilTemp = coilTemp;
    }

    public BigDecimal getOilTemp() {
        return oilTemp;
    }

    public void setOilTemp(BigDecimal oilTemp) {
        this.oilTemp = oilTemp;
    }

    public BigDecimal getBodyTemp() {
        return bodyTemp;
    }

    public void setBodyTemp(BigDecimal bodyTemp) {
        this.bodyTemp = bodyTemp;
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

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public BigDecimal getConstantTime() {
        return constantTime;
    }

    public void setConstantTime(BigDecimal constantTime) {
        this.constantTime = constantTime;
    }

    public BigDecimal getInverterTime() {
        return inverterTime;
    }

    public void setInverterTime(BigDecimal inverterTime) {
        this.inverterTime = inverterTime;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
