package com.waterapp.service.gateway;


import com.waterapp.domain.gateway.Gateway;
import com.waterapp.repository.gateway.GatewayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class GatewayService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private GatewayRepository repository;

    public Gateway create(Gateway model) {

        model.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        model.setCtime(new Date());
        repository.save(model);

        log.info("创建新接口: {}", model.getName());

        return model;
    }

    public Gateway save(Gateway model) {
        Gateway temp = repository.findOne(model.getId());
        Assert.notNull(temp, "接口不存在");

        temp.setName(model.getName());
        temp.setUrl(model.getUrl());
        temp.setParam(model.getParam());
        repository.save(temp);

        log.info("接口 {} 信息更改", temp.getName());

        return temp;
    }

    public void delete(String id) {
        Gateway temp = repository.findOne(id);
        Assert.notNull(temp, "接口不存在");

        repository.delete(id);

        log.info("删除接口: {}", temp.getName());
    }

    public Gateway findOne(String id) {
        Gateway temp = repository.findOne(id);
        Assert.notNull(temp, "接口不存在");

        return temp;
    }

    public Page<Gateway> findAll(PageRequest page, Gateway model) {
        if (model.getName() != null) return repository.findByNameLike(page, model.getName());
        else return repository.findByIdNotNull(page);
    }
}
