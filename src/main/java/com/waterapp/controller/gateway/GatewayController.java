package com.waterapp.controller.gateway;


import com.waterapp.domain.commons.PageParam;
import com.waterapp.domain.commons.Result;
import com.waterapp.domain.gateway.Gateway;
import com.waterapp.service.gateway.GatewayService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/gateway")
public class GatewayController {

    @Resource
    private GatewayService service;

    @PostMapping
    public Result create(@RequestBody Gateway gateway) {
        Result result = new Result();
        try {
            result.setData(service.create(gateway));
            result.setMsg("添加成功");
        } catch (Exception ex) {
            result.setError(ex.getMessage() != null ? ex.getMessage() : ex.toString());
        }
        return result;
    }

    @PutMapping("/{id}")
    public Result save(@PathVariable String id, @RequestBody Gateway gateway) {
        Result result = new Result();
        try {
            gateway.setId(id);
            result.setData(service.save(gateway));
            result.setMsg("保存成功");
        } catch (Exception ex) {
            result.setError(ex.getMessage() != null ? ex.getMessage() : ex.toString());
        }
        return result;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        Result result = new Result();
        try {
            service.delete(id);
            result.setMsg("删除成功");
        } catch (Exception ex) {
            result.setError(ex.getMessage() != null ? ex.getMessage() : ex.toString());
        }
        return result;
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable String id) {
        Result result = new Result();
        try {
            result.setData(service.findOne(id));
            result.setMsg("加载成功");
        } catch (Exception ex) {
            result.setError(ex.getMessage() != null ? ex.getMessage() : ex.toString());
        }
        return result;
    }

    @GetMapping
    public Result findAll(PageParam param, Gateway model) {
        Result result = new Result();
        try {
            PageRequest pageRequest = param.getPageRequest();
            result.setData(service.findAll(pageRequest, model));
            result.setMsg("加载成功");
        } catch (Exception ex) {
            result.setError(ex.getMessage() != null ? ex.getMessage() : ex.toString());
        }
        return result;
    }

}
