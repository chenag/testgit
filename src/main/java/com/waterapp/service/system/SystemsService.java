package com.waterapp.service.system;

import com.alibaba.fastjson.JSONObject;
import com.waterapp.domain.pipe.Pipe;
import com.waterapp.domain.system.Systems;
import com.waterapp.repository.pipe.PipeRepository;
import com.waterapp.repository.system.SystemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class SystemsService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private SystemsRepository repository;
    @Resource
    private PipeRepository pipeRepository;

    /**
     * 能耗
     * @param time
     * @return
     */
    public JSONObject findOneDayEnergy(String time){
        String[] times={"00:00:00","04:00:00","08:00:00","12:00:00","16:00:00","20:00:00","23:59:59"};
        JSONObject returnJson=new JSONObject();
        for (int i=0;i<times.length-1;i++){
            List<Systems> startSystemsList=repository.findOneLastTime(time+" "+times[i]);
            List<Systems> endSystemsList=repository.findOneLastTime(time+" "+times[i+1]);
            List<Pipe> startPipeList=pipeRepository.findOneLasttime(time+" "+times[i]);
            List<Pipe> endPipeList=pipeRepository.findOneLasttime(time+" "+times[i+1]);
            if (startSystemsList==null || startSystemsList.size()<=0 || endSystemsList==null || endSystemsList.size()<=0){
                Assert.notNull(endSystemsList, "接口不存在");
            }
            if (startPipeList==null || startPipeList.size()<=0 || endPipeList==null || endPipeList.size()<=0){
                Assert.notNull(endPipeList, "接口不存在");
            }
            Systems startSystemsModel=startSystemsList.get(0);
            Systems endSystemsModel=endSystemsList.get(0);
            Pipe startPipeModel=startPipeList.get(0);
            Pipe endPipeModel=startPipeList.get(0);
            //该时间段内使用的电量
            BigDecimal beforEConsumption=new BigDecimal("0");
            if (startSystemsModel.geteConsumption()!=null && !startSystemsModel.geteConsumption().equals("")){
                beforEConsumption=startSystemsModel.geteConsumption();
            }
            BigDecimal afterEConsumption=new BigDecimal("0");
            if (endSystemsModel.geteConsumption()!=null && !endSystemsModel.geteConsumption().equals("")){
                afterEConsumption=endSystemsModel.geteConsumption();
            }
            BigDecimal eConsumption=afterEConsumption.multiply(beforEConsumption);
            //该时间段内管道累计流量
            BigDecimal beforVolume=new BigDecimal("0");
            if (startPipeModel.getTachometerAll()!=null && !startPipeModel.getTachometerAll().equals("")){
                beforVolume=startPipeModel.getTachometerAll();
            }
            BigDecimal afterVolume=new BigDecimal("0");
            if (endPipeModel.getTachometerAll()!=null && !endPipeModel.getTachometerAll().equals("")){
                afterVolume=endPipeModel.getTachometerAll();
            }
            BigDecimal volume=afterVolume.multiply(beforVolume);
            if (volume==null || volume.equals("") || volume.compareTo(new BigDecimal("0"))<1){
                volume=new BigDecimal("1");
            }

            JSONObject tempjson=new JSONObject();
            tempjson.put("energy",eConsumption.divide(volume)+"");
            returnJson.put(times[i],tempjson);
        }
        return returnJson;
    }

    /**
     * 系统统计
     * @param time
     * @return
     */
    public JSONObject findOneDaySystems(String time){
        String[] times={"00:00:00","04:00:00","08:00:00","12:00:00","16:00:00","20:00:00","23:59:59"};
        JSONObject returnJson=new JSONObject();
        for (int i=0;i<times.length-1;i++){
            List<Systems> list=repository.findOneAvgByDay(time+" "+times[i],time+" "+times[i+1]);
            if (list==null || list.size()<=0){
                Assert.notNull(list, "接口不存在");
            }
            Systems model=list.get(0);
            JSONObject tempjson=new JSONObject();
            tempjson.put("voltagea",model.getVoltagea()!= null ? model.getVoltagea()+"" : "0");
            tempjson.put("voltageb",model.getVoltageb()!= null ? model.getVoltageb()+"" : "0");
            tempjson.put("voltagec",model.getVoltagec()!= null ? model.getVoltagec()+"" : "0");
            tempjson.put("currenta",model.getCurrenta()!= null ? model.getCurrenta()+"" : "0");
            tempjson.put("currentb",model.getCurrentb()!= null ? model.getCurrentb()+"" : "0");
            tempjson.put("currentc",model.getCurrentc()!= null ? model.getCurrentc()+"" : "0");
            returnJson.put(times[i],tempjson);
        }
        return returnJson;
    }
}
