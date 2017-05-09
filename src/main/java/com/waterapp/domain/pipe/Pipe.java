package com.waterapp.domain.pipe;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="tb_pipe")
public class Pipe {
    @Id
    private String id;
    private String deviceCode;
    private BigDecimal importPressure;
    private BigDecimal exportPressure;
    private BigDecimal importTemp;
    private BigDecimal exportTemp;
    private BigDecimal tachometer;
    private BigDecimal tachometerAll;
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

    public BigDecimal getImportPressure() {
        return importPressure;
    }

    public void setImportPressure(BigDecimal importPressure) {
        this.importPressure = importPressure;
    }

    public BigDecimal getExportPressure() {
        return exportPressure;
    }

    public void setExportPressure(BigDecimal exportPressure) {
        this.exportPressure = exportPressure;
    }

    public BigDecimal getImportTemp() {
        return importTemp;
    }

    public void setImportTemp(BigDecimal importTemp) {
        this.importTemp = importTemp;
    }

    public BigDecimal getExportTemp() {
        return exportTemp;
    }

    public void setExportTemp(BigDecimal exportTemp) {
        this.exportTemp = exportTemp;
    }

    public BigDecimal getTachometer() {
        return tachometer;
    }

    public void setTachometer(BigDecimal tachometer) {
        this.tachometer = tachometer;
    }

    public BigDecimal getTachometerAll() {
        return tachometerAll;
    }

    public void setTachometerAll(BigDecimal tachometerAll) {
        this.tachometerAll = tachometerAll;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
