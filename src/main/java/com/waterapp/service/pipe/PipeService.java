package com.waterapp.service.pipe;

import com.alibaba.fastjson.JSONObject;
import com.waterapp.domain.pipe.Pipe;
import com.waterapp.repository.pipe.PipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PipeService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private PipeRepository repository;


    public JSONObject findOneDayPipe(String time){
        String[] times={"00:00:00","04:00:00","08:00:00","12:00:00","16:00:00","20:00:00","23:59:59"};
        JSONObject returnJson=new JSONObject();
        for (int i=0;i<times.length-1;i++){
            List<Pipe> list=repository.findPipeByDay(time+" "+times[i],time+" "+times[i+1]);
            if (list==null || list.size()==0){
                Assert.notNull(list, "接口不存在");
            }
            Pipe model=list.get(0);
            JSONObject tempjson=new JSONObject();
            tempjson.put("importPressure",model.getImportPressure()!= null ? model.getImportPressure()+"" : "0");
            tempjson.put("exportPressure",model.getExportPressure()!= null ? model.getExportPressure()+"" : "0");
            tempjson.put("importTemp",model.getImportTemp()!= null ? model.getImportTemp()+"" : "0");
            tempjson.put("exportTemp",model.getExportTemp()!= null ? model.getExportTemp()+"" : "0");
            tempjson.put("tachometer",model.getTachometer()!= null ? model.getTachometer()+"" : "0");
            returnJson.put(times[i],tempjson);
        }


        return returnJson;
    }
}
