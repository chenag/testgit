package com.waterapp.service.transducer;

import com.alibaba.fastjson.JSONObject;
import com.waterapp.domain.transducer.Transducer6;
import com.waterapp.repository.transducer.Transducer6Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class Transducer6Service {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    Transducer6Repository repository;

    public JSONObject findOneDayTransducer6(String time){
        String[] times={"00:00:00","04:00:00","08:00:00","12:00:00","16:00:00","20:00:00","23:59:59"};
        JSONObject returnJson=new JSONObject();
        for (int i=0;i<times.length-1;i++){
            List<Transducer6> list=new ArrayList<>();
            list=repository.findTransducer6ByDay(time+" "+times[i],time+" "+times[i+1]);
            if (list!=null && list.size()>0){
                JSONObject tempjson=new JSONObject();
                tempjson.put("inverterVoltage","0");
                tempjson.put("inverterCurrent","0");
                tempjson.put("speed","0");
                tempjson.put("freq","0");
                returnJson.put(times[i],tempjson);
            }
            boolean b=list.isEmpty();
            Transducer6 model=list.get(0);
            JSONObject tempjson=new JSONObject();
            tempjson.put("inverterVoltage",model.getInverterVoltage()!= null ? model.getInverterVoltage() : "0");
            tempjson.put("inverterCurrent",model.getInverterCurrent()!= null ? model.getInverterCurrent() : "0");
            tempjson.put("speed",model.getSpeed()!=null ? model.getSpeed() : "0");
            tempjson.put("freq",model.getFreq()!=null ?model.getFreq() : "0");
            returnJson.put(times[i],tempjson);
        }
        return returnJson;
    }

}
