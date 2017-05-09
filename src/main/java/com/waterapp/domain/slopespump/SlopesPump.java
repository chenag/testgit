package com.waterapp.domain.slopespump;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tb_slopes_pump")
public class SlopesPump {
    @Id
    private String id;
    private String deviceCode;
    private BigDecimal warningLevel;
    private BigDecimal startLevel;
    private BigDecimal stopLevel;
    private BigDecimal sumpLevel;
    private Integer remote1;
    private Integer remote2;
    private Integer remote3;
    private Integer superLevel;
    private Integer highLevel;
    private Integer midLevel;
    private Integer lowLevel;
    private Integer inferiorLevel;
    private Integer isRemote;
    private Integer pumpState1;
    private Integer pumpState2;
    private Integer pumpState3;

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

    public BigDecimal getWarningLevel() {
        return warningLevel;
    }

    public void setWarningLevel(BigDecimal warningLevel) {
        this.warningLevel = warningLevel;
    }

    public BigDecimal getStartLevel() {
        return startLevel;
    }

    public void setStartLevel(BigDecimal startLevel) {
        this.startLevel = startLevel;
    }

    public BigDecimal getStopLevel() {
        return stopLevel;
    }

    public void setStopLevel(BigDecimal stopLevel) {
        this.stopLevel = stopLevel;
    }

    public BigDecimal getSumpLevel() {
        return sumpLevel;
    }

    public void setSumpLevel(BigDecimal sumpLevel) {
        this.sumpLevel = sumpLevel;
    }

    public Integer getRemote1() {
        return remote1;
    }

    public void setRemote1(Integer remote1) {
        this.remote1 = remote1;
    }

    public Integer getRemote2() {
        return remote2;
    }

    public void setRemote2(Integer remote2) {
        this.remote2 = remote2;
    }

    public Integer getRemote3() {
        return remote3;
    }

    public void setRemote3(Integer remote3) {
        this.remote3 = remote3;
    }

    public Integer getSuperLevel() {
        return superLevel;
    }

    public void setSuperLevel(Integer superLevel) {
        this.superLevel = superLevel;
    }

    public Integer getHighLevel() {
        return highLevel;
    }

    public void setHighLevel(Integer highLevel) {
        this.highLevel = highLevel;
    }

    public Integer getMidLevel() {
        return midLevel;
    }

    public void setMidLevel(Integer midLevel) {
        this.midLevel = midLevel;
    }

    public Integer getLowLevel() {
        return lowLevel;
    }

    public void setLowLevel(Integer lowLevel) {
        this.lowLevel = lowLevel;
    }

    public Integer getInferiorLevel() {
        return inferiorLevel;
    }

    public void setInferiorLevel(Integer inferiorLevel) {
        this.inferiorLevel = inferiorLevel;
    }

    public Integer getIsRemote() {
        return isRemote;
    }

    public void setIsRemote(Integer isRemote) {
        this.isRemote = isRemote;
    }

    public Integer getPumpState1() {
        return pumpState1;
    }

    public void setPumpState1(Integer pumpState1) {
        this.pumpState1 = pumpState1;
    }

    public Integer getPumpState2() {
        return pumpState2;
    }

    public void setPumpState2(Integer pumpState2) {
        this.pumpState2 = pumpState2;
    }

    public Integer getPumpState3() {
        return pumpState3;
    }

    public void setPumpState3(Integer pumpState3) {
        this.pumpState3 = pumpState3;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
