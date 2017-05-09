package com.waterapp.repository.pump;

import com.waterapp.domain.pump.Pump4;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Pump4Repository extends PagingAndSortingRepository<Pump4, String> {

    @Query(value = "select DISTINCT a.id,a.device_code, a.voltagea,a.voltageb,a.voltagec,a.currenta,a.currentb,a.currentc,a.powera,a.powerb,a.powerc,a.freq,a.manual,a.`mode`,a.temp_up,a.temp_down,a.oil_temp,"+
    "a.body_temp,a.inverter_voltage,a.inverter_current,a.speed,a.error_code,a.constant_time,a.inverter_time,a.ctime,round(AVG(a.coil_temp),3) as coil_temp,round(AVG(a.oil_temp),3) as oil_temp," +
     "round(AVG(a.temp_up),3) as temp_up,round(AVG(a.temp_down),3) as temp_down from tb_pump4 a where a.ctime>=:starttime and a.ctime<=:endtime",nativeQuery = true)
    List<Pump4> findPump4ByDay(@Param("starttime") String starttime, @Param("endtime") String endtime);

    /**
     * 根据时间取出最后一个水泵4信息
     * @param lasttime
     * @return
     */
    @Query(value = "SELECT a.* FROM tb_pump4 a WHERE a.ctime<=:lasttime ORDER BY a.ctime DESC LIMIT 0,1",nativeQuery = true)
    List<Pump4> findPump4ByRunTime(@Param("lasttime")String lasttime);
}
