package com.wyl.test.constants;

/**
 * @Auther: wangyulin
 * @Date: 2018/8/19 11:50
 * @Description:
 */
public enum Gender {

    MALE(1, "男"),
    FEMALE(2, "女"),
    SECRECY(3, "保密");

    private String name;
    private int value;

    private Gender(int value, String name){
        this.value = value;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
