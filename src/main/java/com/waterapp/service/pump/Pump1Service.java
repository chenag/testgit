package com.waterapp.service.pump;

import com.alibaba.fastjson.JSONObject;
import com.waterapp.domain.pump.*;
import com.waterapp.repository.pump.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class Pump1Service {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private Pump1Repository repository;
    @Resource
    private Pump2Repository p2repository;
    @Resource
    private Pump3Repository p3repository;
    @Resource
    private Pump4Repository p4repository;
    @Resource
    private Pump5Repository p5repository;
    @Resource
    private Pump6Repository p6repository;


    /**
     * 水泵信息
     * @param time
     * @return
     */
    public JSONObject findOneDayPump1(String time){
        String[] times={"00:00:00","04:00:00","08:00:00","12:00:00","16:00:00","20:00:00","23:59:59"};
        JSONObject returnJson=new JSONObject();
        for (int i=0;i<times.length-1;i++){
            List<Pump1> list=repository.findPump1ByDay(time+" "+times[i],time+" "+times[i+1]);
            if (list==null || list.size()==0){
                Assert.notNull(list, "接口不存在");
            }
            Pump1 model=list.get(0);
            JSONObject tempjson=new JSONObject();
            tempjson.put("coilTemp",model.getCoilTemp()!= null ? model.getCoilTemp()+"" : "0");
            tempjson.put("oilTemp",model.getOilTemp()!= null ? model.getOilTemp()+"" : "0");
            tempjson.put("tempUp",model.getTempUp()!= null ? model.getTempUp()+"" : "0");
            tempjson.put("tempDown",model.getTempDown()!= null ? model.getTempDown()+"" : "0");
            returnJson.put(times[i],tempjson);
        }
        return returnJson;
    }

    /**
     * 水泵运行时间
     * @param time
     * @return
     */
    public JSONObject findOneDayRunTime(String time){
        String[] times={" 00:00:00"," 23:59:59"};
        JSONObject returnJson=new JSONObject();
        List<Pump1> beforlist1=repository.findPump1ByRunTime(time+times[0]);
        List<Pump1> afterlist1=repository.findPump1ByRunTime(time+times[1]);
        List<Pump2> beforlist2=p2repository.findPump2ByRunTime(time+times[0]);
        List<Pump2> afterlist2=p2repository.findPump2ByRunTime(time+times[1]);
        List<Pump3> beforlist3=p3repository.findPump3ByRunTime(time+times[0]);
        List<Pump3> afterlist3=p3repository.findPump3ByRunTime(time+times[1]);
        List<Pump4> beforlist4=p4repository.findPump4ByRunTime(time+times[0]);
        List<Pump4> afterlist4=p4repository.findPump4ByRunTime(time+times[1]);
        List<Pump5> beforlist5=p5repository.findPump5ByRunTime(time+times[0]);
        List<Pump5> afterlist5=p5repository.findPump5ByRunTime(time+times[1]);
        List<Pump6> beforlist6=p6repository.findPump6ByRunTime(time+times[0]);
        List<Pump6> afterlist6=p6repository.findPump6ByRunTime(time+times[1]);
        //水泵1信息
        if (beforlist1!=null && beforlist1.size()>0 && afterlist1!=null && afterlist1.size()>0){
            Assert.notNull(beforlist1, "接口不存在");
            Pump1 beformodel=beforlist1.get(0);
            Pump1 aftermodel=afterlist1.get(0);
            JSONObject tempjson=new JSONObject();
            BigDecimal constantTime=new BigDecimal("0");
            BigDecimal inverterTime=new BigDecimal("0");
            BigDecimal countTime=new BigDecimal("0");
            //工频运行时间
            if (aftermodel.getConstantTime()!=null && !aftermodel.getConstantTime().equals("")){
                constantTime=aftermodel.getConstantTime();
            }
            if (beformodel.getConstantTime()!=null && !beformodel.getConstantTime().equals("")){
                constantTime=constantTime.subtract(beformodel.getConstantTime());
            }
            //变频运行时间
            if (aftermodel.getInverterTime()!=null && !aftermodel.getInverterTime().equals("")){
                inverterTime=aftermodel.getInverterTime();
            }
            if (beformodel.getInverterTime()!=null && !beformodel.getInverterTime().equals("")){
                inverterTime=inverterTime.subtract(beformodel.getInverterTime());
            }
            countTime=constantTime.add(inverterTime);
            tempjson.put("constantTime",constantTime);
            tempjson.put("inverterTime",inverterTime);
            tempjson.put("countTime",countTime!= null ? countTime+"" : "0");
            returnJson.put("pump1",tempjson);
        }
        //水泵2信息
        if (beforlist2!=null && beforlist2.size()>0 && afterlist2!=null && afterlist2.size()>0){
            Assert.notNull(beforlist2, "接口不存在");
            Pump2 beformodel=beforlist2.get(0);
            Pump2 aftermodel=afterlist2.get(0);
            JSONObject tempjson=new JSONObject();
            BigDecimal constantTime=new BigDecimal("0");
            BigDecimal inverterTime=new BigDecimal("0");
            BigDecimal countTime=new BigDecimal("0");
            //工频运行时间
            if (aftermodel.getConstantTime()!=null && !aftermodel.getConstantTime().equals("")){
                constantTime=aftermodel.getConstantTime();
            }
            if (beformodel.getConstantTime()!=null && !beformodel.getConstantTime().equals("")){
                constantTime=constantTime.subtract(beformodel.getConstantTime());
            }
            //变频运行时间
            if (aftermodel.getInverterTime()!=null && !aftermodel.getInverterTime().equals("")){
                inverterTime=aftermodel.getInverterTime();
            }
            if (beformodel.getInverterTime()!=null && !beformodel.getInverterTime().equals("")){
                inverterTime=inverterTime.subtract(beformodel.getInverterTime());
            }
            countTime=constantTime.add(inverterTime);
            tempjson.put("constantTime",constantTime);
            tempjson.put("inverterTime",inverterTime);
            tempjson.put("countTime",countTime!= null ? countTime+"" : "0");
            returnJson.put("pump2",tempjson);
        }
        //水泵3信息
        if (beforlist3!=null && beforlist3.size()>0 && afterlist3!=null && afterlist3.size()>0){
            Assert.notNull(beforlist3, "接口不存在");
            Pump3 beformodel=beforlist3.get(0);
            Pump3 aftermodel=afterlist3.get(0);
            JSONObject tempjson=new JSONObject();
            BigDecimal constantTime=new BigDecimal("0");
            BigDecimal inverterTime=new BigDecimal("0");
            BigDecimal countTime=new BigDecimal("0");
            //工频运行时间
            if (aftermodel.getConstantTime()!=null && !aftermodel.getConstantTime().equals("")){
                constantTime=aftermodel.getConstantTime();
            }
            if (beformodel.getConstantTime()!=null && !beformodel.getConstantTime().equals("")){
                constantTime=constantTime.subtract(beformodel.getConstantTime());
            }
            //变频运行时间
            if (aftermodel.getInverterTime()!=null && !aftermodel.getInverterTime().equals("")){
                inverterTime=aftermodel.getInverterTime();
            }
            if (beformodel.getInverterTime()!=null && !beformodel.getInverterTime().equals("")){
                inverterTime=inverterTime.subtract(beformodel.getInverterTime());
            }
            countTime=constantTime.add(inverterTime);
            tempjson.put("constantTime",constantTime);
            tempjson.put("inverterTime",inverterTime);
            tempjson.put("countTime",countTime!= null ? countTime+"" : "0");
            returnJson.put("pump3",tempjson);
        }
        //水泵4信息
        if (beforlist4!=null && beforlist4.size()>0 && afterlist4!=null && afterlist4.size()>0){
            Assert.notNull(beforlist4, "接口不存在");
            Pump4 beformodel=beforlist4.get(0);
            Pump4 aftermodel=afterlist4.get(0);
            JSONObject tempjson=new JSONObject();
            BigDecimal constantTime=new BigDecimal("0");
            BigDecimal inverterTime=new BigDecimal("0");
            BigDecimal countTime=new BigDecimal("0");
            //工频运行时间
            if (aftermodel.getConstantTime()!=null && !aftermodel.getConstantTime().equals("")){
                constantTime=aftermodel.getConstantTime();
            }
            if (beformodel.getConstantTime()!=null && !beformodel.getConstantTime().equals("")){
                constantTime=constantTime.subtract(beformodel.getConstantTime());
            }
            //变频运行时间
            if (aftermodel.getInverterTime()!=null && !aftermodel.getInverterTime().equals("")){
                inverterTime=aftermodel.getInverterTime();
            }
            if (beformodel.getInverterTime()!=null && !beformodel.getInverterTime().equals("")){
                inverterTime=inverterTime.subtract(beformodel.getInverterTime());
            }
            countTime=constantTime.add(inverterTime);
            tempjson.put("constantTime",constantTime);
            tempjson.put("inverterTime",inverterTime);
            tempjson.put("countTime",countTime!= null ? countTime+"" : "0");
            returnJson.put("pump4",tempjson);
        }
        //水泵5信息
        if (beforlist5!=null && beforlist5.size()>0 && afterlist5!=null && afterlist5.size()>0){
            Assert.notNull(beforlist5, "接口不存在");
            Pump5 beformodel=beforlist5.get(0);
            Pump5 aftermodel=afterlist5.get(0);
            JSONObject tempjson=new JSONObject();
            BigDecimal constantTime=new BigDecimal("0");
            BigDecimal inverterTime=new BigDecimal("0");
            BigDecimal countTime=new BigDecimal("0");
            //工频运行时间
            if (aftermodel.getConstantTime()!=null && !aftermodel.getConstantTime().equals("")){
                constantTime=aftermodel.getConstantTime();
            }
            if (beformodel.getConstantTime()!=null && !beformodel.getConstantTime().equals("")){
                constantTime=constantTime.subtract(beformodel.getConstantTime());
            }
            //变频运行时间
            if (aftermodel.getInverterTime()!=null && !aftermodel.getInverterTime().equals("")){
                inverterTime=aftermodel.getInverterTime();
            }
            if (beformodel.getInverterTime()!=null && !beformodel.getInverterTime().equals("")){
                inverterTime=inverterTime.subtract(beformodel.getInverterTime());
            }
            countTime=constantTime.add(inverterTime);
            tempjson.put("constantTime",constantTime);
            tempjson.put("inverterTime",inverterTime);
            tempjson.put("countTime",countTime!= null ? countTime+"" : "0");
            returnJson.put("pump5",tempjson);
        }
        //水泵6信息
        if (beforlist6!=null && beforlist6.size()>0 && afterlist6!=null && afterlist6.size()>0){
            Assert.notNull(beforlist6, "接口不存在");
            Pump6 beformodel=beforlist6.get(0);
            Pump6 aftermodel=afterlist6.get(0);
            JSONObject tempjson=new JSONObject();
            BigDecimal constantTime=new BigDecimal("0");
            BigDecimal inverterTime=new BigDecimal("0");
            BigDecimal countTime=new BigDecimal("0");
            //工频运行时间
            if (aftermodel.getConstantTime()!=null && !aftermodel.getConstantTime().equals("")){
                constantTime=aftermodel.getConstantTime();
            }
            if (beformodel.getConstantTime()!=null && !beformodel.getConstantTime().equals("")){
                constantTime=constantTime.subtract(beformodel.getConstantTime());
            }
            //变频运行时间
            if (aftermodel.getInverterTime()!=null && !aftermodel.getInverterTime().equals("")){
                inverterTime=aftermodel.getInverterTime();
            }
            if (beformodel.getInverterTime()!=null && !beformodel.getInverterTime().equals("")){
                inverterTime=inverterTime.subtract(beformodel.getInverterTime());
            }
            countTime=constantTime.add(inverterTime);
            tempjson.put("constantTime",constantTime);
            tempjson.put("inverterTime",inverterTime);
            tempjson.put("countTime",countTime!= null ? countTime+"" : "0");
            returnJson.put("pump6",tempjson);
        }

        return returnJson;
    }

}
