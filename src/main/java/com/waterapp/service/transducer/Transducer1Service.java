package com.waterapp.service.transducer;

import com.alibaba.fastjson.JSONObject;
import com.waterapp.domain.box.Box;
import com.waterapp.domain.transducer.Transducer1;
import com.waterapp.repository.transducer.Transducer1Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Transducer1Service {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    Transducer1Repository repository;

    public JSONObject findOneDayTransducer1(String time){
        String[] times={"00:00:00","04:00:00","08:00:00","12:00:00","16:00:00","20:00:00","23:59:59"};
        JSONObject returnJson=new JSONObject();
        for (int i=0;i<times.length-1;i++){
            List<Transducer1> list=repository.findTransducer1ByDay(time+" "+times[i],time+" "+times[i+1]);
            if (list!=null && list.size()>0){
                Assert.notNull(list, "接口不存在");
            }
            Transducer1 model=list.get(0);
            JSONObject tempjson=new JSONObject();
            tempjson.put("inverterVoltage",model.getInverterVoltage()!= null ? model.getInverterVoltage()+"" : "0");
            tempjson.put("inverterCurrent",model.getInverterCurrent()!= null ? model.getInverterCurrent()+"" : "0");
            tempjson.put("speed",model.getSpeed()!= null ? model.getSpeed()+"" : "0");
            tempjson.put("freq",model.getFreq()!= null ? model.getFreq()+"" : "0");
            returnJson.put(times[i],tempjson);
        }
        return returnJson;
    }

}
