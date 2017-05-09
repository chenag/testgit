package com.waterapp.controller.user;

import com.waterapp.domain.commons.PageParam;
import com.waterapp.domain.commons.Result;
import com.waterapp.domain.user.User;
import com.waterapp.service.user.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/5/2.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UserService service;

    @PostMapping
    public Result findOne(@RequestBody User user) {
        Result result = new Result();
        try {
            result.setData(service.findOne(user.getUsername(), user.getPassword()));
            result.setMsg("加载成功");
        } catch (Exception ex) {
            result.setError(ex.getMessage() != null ? ex.getMessage() : ex.toString());
        }
        return result;
    }
    @PutMapping("/{id}")
    public Result save(@PathVariable Integer id, @RequestBody User user) {
        Result result = new Result();
        if (user.getPassword()==null || user.getPassword().equals("")){
            result.setError("请输入密码");
        }
        try {
            user.setId(id);
            result.setData(service.save(user));
            result.setMsg("保存成功");
        } catch (Exception ex) {
            result.setError(ex.getMessage() != null ? ex.getMessage() : ex.toString());
        }
        return result;
    }

    @PutMapping("/reset/{id}")
    public Result resetPassword(@PathVariable Integer id, @RequestBody User user) {
        Result result = new Result();
        try {
            user.setId(id);
            user.setPassword("123456");
            result.setData(service.save(user));
            result.setMsg("保存成功");
        } catch (Exception ex) {
            result.setError(ex.getMessage() != null ? ex.getMessage() : ex.toString());
        }
        return result;
    }
}
