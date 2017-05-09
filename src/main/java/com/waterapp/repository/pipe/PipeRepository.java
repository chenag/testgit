package com.waterapp.repository.pipe;


import com.waterapp.domain.pipe.Pipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PipeRepository extends PagingAndSortingRepository<Pipe,String> {
    /**
     * 查询管道统计
     * @param starttime
     * @param endtime
     * @return
     */
    @Query(value = "select DISTINCT a.id,a.device_code,a.tachometer_all,a.ctime,round(AVG(a.import_pressure),3) as import_pressure,round(AVG(a.export_pressure),3) as export_pressure," +
            "round(AVG(a.import_temp),3) as import_temp,round(AVG(a.export_temp),3) as export_temp,round(AVG(a.tachometer),3) as tachometer" +
            " from tb_pipe a where a.ctime>=:starttime and a.ctime<=:endtime",nativeQuery = true)
    List<Pipe> findPipeByDay(@Param("starttime") String starttime, @Param("endtime") String endtime);

    /**
     * 查询时间前最后一次管道信息
     * @param lasttime
     * @return
     */
    @Query(value = "SELECT a.* FROM tb_pipe a WHERE a.ctime<=:lasttime ORDER BY a.ctime DESC LIMIT 0,1",nativeQuery = true)
    List<Pipe> findOneLasttime(@Param("lasttime")String lasttime);
}
