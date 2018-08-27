package com.wyl.test.entity;

import com.wyl.test.constants.Gender;

import javax.persistence.*;
import java.util.Date;

/**
 * @Auther: wangyulin
 * @Date: 2018/8/19 11:47
 * @Description:
 */
@Entity
@Table(name = " user")
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    private String email;

    private Date birth;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
