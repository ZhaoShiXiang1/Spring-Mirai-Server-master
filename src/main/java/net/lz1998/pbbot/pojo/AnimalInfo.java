package net.lz1998.pbbot.pojo;

import org.springframework.stereotype.Component;


@Component
public class AnimalInfo {
          private String animal_name;
          private Integer animal_code;
          private Integer animal_level;
          private String animal_line;
          private Integer animal_hp;
          private Integer animal_ce;
          private Integer animal_power;

    @Override
    public String toString() {
        return "AnimalInfo{" +
                "animal_name='" + animal_name + '\'' +
                ", animal_code=" + animal_code +
                ", animal_level=" + animal_level +
                ", animal_line='" + animal_line + '\'' +
                ", animal_hp=" + animal_hp +
                ", animal_ce=" + animal_ce +
                ", animal_power=" + animal_power +
                '}';
    }

    public AnimalInfo() {
    }

    public AnimalInfo(String animal_name, Integer animal_code, Integer animal_level, String animal_line, Integer animal_hp, Integer animal_ce, Integer animal_power) {
        this.animal_name = animal_name;
        this.animal_code = animal_code;
        this.animal_level = animal_level;
        this.animal_line = animal_line;
        this.animal_hp = animal_hp;
        this.animal_ce = animal_ce;
        this.animal_power = animal_power;
    }
}
