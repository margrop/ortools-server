package me.ymq.ortools.jm.vo;

import io.swagger.annotations.ApiModelProperty;

public class JmWorkerVo {
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("工作类型：1裁判/2司线")
    private Long job = 1L;
    @ApiModelProperty("职级：11国际/21亚洲/30国家级/31国家一级/32国家二级/33国家三级/99其他")
    private Long grade = 99L;
    @ApiModelProperty("评价：92A+/90A/88A-/82B+/80B/78B-/72C+/70C/68C-/62D+/60D/58D-")
    private Integer evaluate = 0;
    @ApiModelProperty("性别：0男，1女")
    private Long sex = 0L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getJob() {
        return job;
    }

    public void setJob(Long job) {
        this.job = job;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }
}
