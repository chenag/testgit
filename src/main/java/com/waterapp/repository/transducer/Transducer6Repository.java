package com.waterapp.repository.transducer;

import com.waterapp.domain.transducer.Transducer6;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Transducer6Repository extends PagingAndSortingRepository<Transducer6,String> {

    @Query(value = "select DISTINCT a.id,a.device_code,a.ctime,a.`mode`,a.error_code,round(AVG(a.inverter_voltage),3) as inverter_voltage,round(AVG(a.inverter_current),3) as inverter_current," +
            "round(AVG(a.speed),3) as speed,round(AVG(a.freq),3) as freq from tb_transducer6 a where a.ctime>=:starttime and a.ctime<=:endtime",nativeQuery = true)
    List<Transducer6> findTransducer6ByDay(@Param("starttime") String starttime, @Param("endtime") String endtime);
}
