package com.waterapp.repository.quality;

import com.waterapp.domain.quality.Quality;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface QualityRepository extends PagingAndSortingRepository<Quality, String> {
    @Query(value = "select DISTINCT a.id,a.device_code,round(AVG(a.ph),3) as ph,round(AVG(a.cl),3) as cl,round(AVG(a.temp),3) as temp,round(AVG(a.turbidity),3) as turbidity,"+
    "round(AVG(a.conductivity),3) as conductivity,round(AVG(a.oxygen),3) as oxygen ,a.voltage,a.current,a.power,a.state,a.remote,a.ctime from tb_qualitys a where a.ctime>=:starttime and a.ctime<=:endtime",nativeQuery = true)
    List<Quality> findQualityByDay(@Param("starttime")String starttime,@Param("endtime")String endtime);
}
