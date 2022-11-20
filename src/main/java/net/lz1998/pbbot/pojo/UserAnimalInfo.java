package net.lz1998.pbbot.pojo;


import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserAnimalInfo {
         private Long   user_id;
         private Integer   animal_id;
         private String    animal_name;
         private String    animal_nickname;
         private Integer   animal_code;
         private Integer   animal_level;
         private Date      animal_create;
         private Date      animal_end;

    @Override
    public String toString() {
        return "UserAnimalInfo{" +
                "user_id=" + user_id +
                ", animal_id=" + animal_id +
                ", animal_name='" + animal_name + '\'' +
                ", animal_nickname='" + animal_nickname + '\'' +
                ", animal_code=" + animal_code +
                ", animal_level=" + animal_level +
                ", animal_create=" + animal_create +
                ", animal_end=" + animal_end +
                '}';
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(Integer animal_id) {
        this.animal_id = animal_id;
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

    public Date getAnimal_create() {
        return animal_create;
    }

    public void setAnimal_create(Date animal_create) {
        this.animal_create = animal_create;
    }

    public Date getAnimal_end() {
        return animal_end;
    }

    public void setAnimal_end(Date animal_end) {
        this.animal_end = animal_end;
    }

    public UserAnimalInfo() {
    }

    public UserAnimalInfo(Long user_id, Integer animal_id, String animal_name, String animal_nickname, Integer animal_code, Integer animal_level, Date animal_create, Date animal_end) {
        this.user_id = user_id;
        this.animal_id = animal_id;
        this.animal_name = animal_name;
        this.animal_nickname = animal_nickname;
        this.animal_code = animal_code;
        this.animal_level = animal_level;
        this.animal_create = animal_create;
        this.animal_end = animal_end;
    }
}
