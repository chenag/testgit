package com.waterapp.controller.quality;

import com.waterapp.domain.commons.Result;
import com.waterapp.domain.quality.Quality;
import com.waterapp.service.quality.QualityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/5/2.
 */
@RestController
@RequestMapping("/api/quality")
public class QualityController {

    @Resource
    private QualityService service;

    @GetMapping("/{time}")
    public Result findOne(@PathVariable String time) {
        Result result = new Result();
//        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd");
        try {
//            String starttime=time+" 00:00:00";
//            String endtime=sdf.format(quality.getCtime())+" 23:59:59";
//            String endtime=time+" 23:59:59";
            result.setData(service.findOneDayQuality(time));
            result.setMsg("加载成功");
        } catch (Exception ex) {
            result.setError(ex.getMessage() != null ? ex.getMessage() : ex.toString());
        }
        return result;
    }
}
