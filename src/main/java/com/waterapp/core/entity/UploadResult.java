package com.waterapp.core.entity;


import java.util.List;

public class UploadResult {

    private Boolean success; //执行结果
    private String data; // 执行结果文本
    private List<String> error; //详细错误信息
    private List<String> actuallyPath;  //文件绝对路径列表
    private List<String> relativePath; // 文件相对路径列表

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public List<String> getActuallyPath() {
        return actuallyPath;
    }

    public void setActuallyPath(List<String> actuallyPath) {
        this.actuallyPath = actuallyPath;
    }

    public List<String> getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(List<String> relativePath) {
        this.relativePath = relativePath;
    }
}
