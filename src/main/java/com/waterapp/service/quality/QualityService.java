package com.waterapp.service.quality;

import com.alibaba.fastjson.JSONObject;
import com.waterapp.domain.quality.Quality;
import com.waterapp.repository.quality.QualityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/2.
 */
@Service
public class QualityService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private QualityRepository repository;

    public JSONObject findOneDayQuality(String time){
        String[] times={"00:00:00","04:00:00","08:00:00","12:00:00","16:00:00","20:00:00","23:59:59"};
        JSONObject returnJson=new JSONObject();
        for (int i=0;i<times.length-1;i++){
            List<Quality> list=repository.findQualityByDay(time+" "+times[i],time+" "+times[i+1]);
            if (list!=null && list.size()>0){
                Assert.notNull(list, "接口不存在");
            }
            Quality model=list.get(0);
            JSONObject tempjson=new JSONObject();
            tempjson.put("ph",model.getPh()!= null ? model.getPh()+"" : "0");
            tempjson.put("cl",model.getCl()!= null ? model.getCl()+"" : "0");
            tempjson.put("temp",model.getTemp()!= null ? model.getTemp()+"" : "0");
            tempjson.put("turbidity",model.getTurbidity()!= null ? model.getTurbidity()+"" : "0");
            tempjson.put("conductivity",model.getConductivity()!= null ? model.getConductivity()+"" : "0");
            tempjson.put("oxygen",model.getOxygen()!= null ? model.getOxygen()+"" : "0");
            returnJson.put(times[i],tempjson);
        }
//        float ph = 0;
//        float cl= 0;
//        float temp= 0;
//        float turbidity= 0;
//        float conductivity= 0;
//        float oxygen= 0;
//        List<HashMap<String,String>> list=repository.findQualityByDay(starttime,endtime);


        return returnJson;
    }
}
