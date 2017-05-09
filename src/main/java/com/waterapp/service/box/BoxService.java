package com.waterapp.service.box;

import com.alibaba.fastjson.JSONObject;
import com.waterapp.domain.box.Box;
import com.waterapp.repository.box.BoxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BoxService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private BoxRepository repository;

    public JSONObject findOneDayBox(String time){
        String[] times={"00:00:00","04:00:00","08:00:00","12:00:00","16:00:00","20:00:00","23:59:59"};
        JSONObject returnJson=new JSONObject();
        for (int i=0;i<times.length-1;i++){
            List<Box> list=repository.findBoxByDay(time+" "+times[i],time+" "+times[i+1]);
            if (list==null || list.size()==0){
                Assert.notNull(list, "接口不存在");
            }
            Box box=list.get(0);
            JSONObject tempjson=new JSONObject();
            tempjson.put("level",box.getLevel()!= null ? box.getLevel()+"" : "0");
            returnJson.put(times[i],tempjson);
        }


        return returnJson;
    }

}
