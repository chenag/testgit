package com.waterapp.repository.slopespump;

import com.waterapp.domain.slopespump.SlopesPump;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlopesPumpRepository extends PagingAndSortingRepository<SlopesPump, String> {
    /**
     * 查询统计
     * @param starttime
     * @param endtime
     * @return
     */
    @Query(value = "select DISTINCT a.id,a.device_code,a.warning_level,a.start_level,a.stop_level,a.remote1,a.remote2,a.remote3,a.super_level,a.high_level,a.mid_level,a.low_level," +
            "a.inferior_level,a.is_remote,a.pump_state1,a.pump_state2,a.pump_state3,a.ctime,round(AVG(a.sump_level),3) as sump_level from tb_slopes_pump a where a.ctime>=:starttime and a.ctime<=:endtime",nativeQuery = true)
    List<SlopesPump> findSlopesPumpByDay(@Param("starttime")String starttime, @Param("endtime")String endtime);
}
