package com.xss.enums;

/**
 * @author XSS
 * @date 2020/7/1
 * @desc
 */
public enum Sysenum {

    NO_START("未开始", 0),
    DOING("进行中", 1),
    END("已结束", 2);

    private String name;
    private Integer value;

    Sysenum() {
    }

    Sysenum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
