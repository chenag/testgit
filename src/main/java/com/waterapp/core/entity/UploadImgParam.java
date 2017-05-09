package com.waterapp.core.entity;

import com.waterapp.core.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class UploadImgParam {
    //前端传入变量
    private String uploadPath;//【上传路径】可选变量，不传则上传到根目录，示例：'img/user'
    private String imgStr;//【base64图片数据】
    private String sizeStr;//【图片压缩大小】可选变量，示例：'[30, 60, 90]'

    //后端生成变量
    private String sysPath;//【服务器真实路径】由controller传入
    private List<String> imgList;//【base64图片数据列表】传入imgStr时自动生成
    private List<Integer> sizeList;//【图片压缩大小列表】传入sizeStr时自动生成

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {//设置字符串自动转换列表
        this.imgStr = imgStr;
        this.imgList = JsonUtils.jsonStrToList(imgStr, String.class);
    }

    public String getSizeStr() {
        return sizeStr;
    }

    public void setSizeStr(String sizeStr) {//设置字符串自动转换列表
        this.sizeStr = sizeStr;
        this.sizeList = JsonUtils.jsonStrToList(sizeStr, Integer.class);
    }

    public String getSysPath() {
        return sysPath;
    }

    public void setSysPath(String sysPath) {
        this.sysPath = sysPath;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public void setImgList(String img) {
        List<String> imgList = new ArrayList<>();
        imgList.add(img);
        this.imgList = imgList;
    }

    public List<Integer> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<Integer> sizeList) {
        this.sizeList = sizeList;
    }
}
