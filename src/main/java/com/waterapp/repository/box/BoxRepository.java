package com.waterapp.repository.box;

import com.waterapp.domain.box.Box;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxRepository extends PagingAndSortingRepository<Box,String> {
    @Query(value = "select DISTINCT a.id,a.device_code,a.ctime,a.vavle,round(AVG(a.`level`),3) as `level`" +
            "from tb_box a where a.ctime>=:starttime and a.ctime<=:endtime",nativeQuery = true)
    List<Box> findBoxByDay(@Param("starttime")String starttime,@Param("endtime")String endtime);
}
