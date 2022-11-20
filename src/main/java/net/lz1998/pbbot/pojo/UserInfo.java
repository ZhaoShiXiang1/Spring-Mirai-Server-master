package net.lz1998.pbbot.pojo;

import org.springframework.stereotype.Component;


import java.util.Date;
@Component
public class UserInfo {
    private Long user_id;
    private String user_create;
    private String animal_name;
    private String animal_nickname;
    private Integer animal_code;
    private Integer animal_level;
    private Integer animal_line;
    private Integer animal_hp;
    private Integer animal_ce;
    private Integer animal_power;
    private Integer animal_level_count;
    private Integer animal_id;
    private Integer safe;
    private Long other_id;

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_id=" + user_id +
                ", user_create='" + user_create + '\'' +
                ", animal_name='" + animal_name + '\'' +
                ", animal_nickname='" + animal_nickname + '\'' +
                ", animal_code=" + animal_code +
                ", animal_level=" + animal_level +
                ", animal_line=" + animal_line +
                ", animal_hp=" + animal_hp +
                ", animal_ce=" + animal_ce +
                ", animal_power=" + animal_power +
                ", animal_level_count=" + animal_level_count +
                ", animal_id=" + animal_id +
                ", safe=" + safe +
                ", other_id=" + other_id +
                '}';
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_create() {
        return user_create;
    }

    public void setUser_create(String user_create) {
        this.user_create = user_create;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    public String getAnimal_nickname() {
        return animal_nickname;
    }

    public void setAnimal_nickname(String animal_nickname) {
        this.animal_nickname = animal_nickname;
    }

    public Integer getAnimal_code() {
        return animal_code;
    }

    public void setAnimal_code(Integer animal_code) {
        this.animal_code = animal_code;
    }

    public Integer getAnimal_level() {
        return animal_level;
    }

    public void setAnimal_level(Integer animal_level) {
        this.animal_level = animal_level;
    }

    public Integer getAnimal_line() {
        return animal_line;
    }

    public void setAnimal_line(Integer animal_line) {
        this.animal_line = animal_line;
    }

    public Integer getAnimal_hp() {
        return animal_hp;
    }

    public void setAnimal_hp(Integer animal_hp) {
        this.animal_hp = animal_hp;
    }

    public Integer getAnimal_ce() {
        return animal_ce;
    }

    public void setAnimal_ce(Integer animal_ce) {
        this.animal_ce = animal_ce;
    }

    public Integer getAnimal_power() {
        return animal_power;
    }

    public void setAnimal_power(Integer animal_power) {
        this.animal_power = animal_power;
    }

    public Integer getAnimal_level_count() {
        return animal_level_count;
    }

    public void setAnimal_level_count(Integer animal_level_count) {
        this.animal_level_count = animal_level_count;
    }

    public Integer getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(Integer animal_id) {
        this.animal_id = animal_id;
    }

    public Integer getSafe() {
        return safe;
    }

    public void setSafe(Integer safe) {
        this.safe = safe;
    }

    public Long getOther_id() {
        return other_id;
    }

    public void setOther_id(Long other_id) {
        this.other_id = other_id;
    }

    public UserInfo() {
    }

    public UserInfo(Long user_id, String user_create, String animal_name, String animal_nickname, Integer animal_code, Integer animal_level, Integer animal_line, Integer animal_hp, Integer animal_ce, Integer animal_power, Integer animal_level_count, Integer animal_id, Integer safe, Long other_id) {
        this.user_id = user_id;
        this.user_create = user_create;
        this.animal_name = animal_name;
        this.animal_nickname = animal_nickname;
        this.animal_code = animal_code;
        this.animal_level = animal_level;
        this.animal_line = animal_line;
        this.animal_hp = animal_hp;
        this.animal_ce = animal_ce;
        this.animal_power = animal_power;
        this.animal_level_count = animal_level_count;
        this.animal_id = animal_id;
        this.safe = safe;
        this.other_id = other_id;
    }
}