package com.waterapp.controller.slopespump;

import com.waterapp.domain.commons.Result;
import com.waterapp.service.slopespump.SlopesPumpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/slopesPump")
public class SlopesPumpController {

    @Resource
    private SlopesPumpService service;

    @GetMapping("/{time}")
    public Result findOne(@PathVariable String time){
        Result result=new Result();
//        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd");
        try {
//            String starttime=time+" 00:00:00";
//            String endtime=sdf.format(quality.getCtime())+" 23:59:59";
//            String endtime=time+" 23:59:59";
            result.setData(service.findOneDaySlopesPump(time));
            result.setMsg("加载成功");
        } catch (Exception ex) {
            result.setError(ex.getMessage() != null ? ex.getMessage() : ex.toString());
        }
        return result;
    }

}
