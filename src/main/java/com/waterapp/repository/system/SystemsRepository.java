package com.waterapp.repository.system;

import com.waterapp.domain.system.Systems;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemsRepository extends PagingAndSortingRepository<Systems,String> {
    /**
     * 根据最后一次相近的时间取系统信息
     * @param lasttime
     * @return
     */
    @Query(value = "SELECT a.* FROM tb_systems a WHERE a.ctime<=:lasttime ORDER BY a.ctime DESC LIMIT 0,1",nativeQuery = true)
    List<Systems> findOneLastTime(@Param("lasttime")String lasttime);

    /**
     * 根据前后时间取出这段时间的电压电流平均值
     * @param starttime
     * @param endtime
     * @return
     */
    @Query(value = "SELECT DISTINCT a.id,a.device_code,a.error_code,a.`mode`,a.pressure,a.change_time,a.sleep_freq,a.sleep_delay,a.aweak_shift,a.lack_level,a.lack_pressure,a.`reset`,a.allow_control," +
            "a.powera,a.powerb,a.powerc,a.e_consumption,a.ctime,round(AVG(a.voltagea),3) as voltagea,round(AVG(a.voltageb),3) as voltageb,round(AVG(a.voltagec),3) as voltagec," +
            "round(AVG(a.currenta),3) as currenta,round(AVG(a.currentb),3) as currentb,round(AVG(a.currentc),3) as currentc FROM tb_systems a where a.ctime>=:starttime and a.ctime<=:endtime",nativeQuery = true)
    List<Systems> findOneAvgByDay(@Param("starttime")String starttime,@Param("endtime")String endtime);
}
