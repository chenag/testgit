package com.waterapp.controller.box;

import com.waterapp.domain.commons.Result;
import com.waterapp.service.box.BoxService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/box")
public class BoxController {
    @Resource
    private BoxService service;

    @GetMapping("/{time}")
    public Result findOne(@PathVariable String time){
        Result result=new Result();
        try {
            result.setData(service.findOneDayBox(time));
            result.setMsg("加载成功");
        } catch (Exception ex) {
            result.setError(ex.getMessage() != null ? ex.getMessage() : ex.toString());
        }
        return result;
    }

}
