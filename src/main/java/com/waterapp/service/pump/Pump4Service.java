package com.waterapp.service.pump;

import com.alibaba.fastjson.JSONObject;
import com.waterapp.domain.pump.Pump4;
import com.waterapp.repository.pump.Pump4Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Pump4Service {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private Pump4Repository repository;

    public JSONObject findOneDayPump4(String time){
        String[] times={"00:00:00","04:00:00","08:00:00","12:00:00","16:00:00","20:00:00","23:59:59"};
        JSONObject returnJson=new JSONObject();
        for (int i=0;i<times.length-1;i++){
            List<Pump4> list=repository.findPump4ByDay(time+" "+times[i],time+" "+times[i+1]);
            if (list!=null && list.size()>0){
                Assert.notNull(list, "接口不存在");
            }
            Pump4 model=list.get(0);
            JSONObject tempjson=new JSONObject();
            tempjson.put("coilTemp",model.getCoilTemp()!= null ? model.getCoilTemp()+"" : "0");
            tempjson.put("oilTemp",model.getOilTemp()!= null ? model.getOilTemp()+"" : "0");
            tempjson.put("tempUp",model.getTempUp()!= null ? model.getTempUp()+"" : "0");
            tempjson.put("tempDown",model.getTempDown()!= null ? model.getTempDown()+"" : "0");
            returnJson.put(times[i],tempjson);
        }


        return returnJson;
    }
}
