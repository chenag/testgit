package com.waterapp.service.user;

import com.waterapp.domain.user.User;
import com.waterapp.repository.user.UserRepository;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/5/2.
 */
@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    public UserRepository repository;

    public User findOne(String username,String password) {
        User temp = repository.getAllUserInfo(username, password);
        Assert.notNull(temp, "接口不存在");

        return temp;
    }

    public User save(User model) {
        User temp = repository.getUserByUserId(model.getId());
        org.springframework.util.Assert.notNull(temp, "接口不存在");

        temp.setPassword(model.getPassword());
        repository.save(temp);

        log.info("接口 {} 用户更改密码", temp.getId());

        return temp;
    }
}
