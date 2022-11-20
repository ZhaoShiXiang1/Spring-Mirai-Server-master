package net.lz1998.pbbot.pojo;


import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
@Component
public class UserPackInfo {
    private Long user_id;
    private Integer goods_id;
    private String goods_name;
    private Integer goods_num;
    private Integer goods_use;

    public UserPackInfo(Long user_id, Integer goods_id, String goods_name, Integer goods_num, Integer goods_use) {
        this.user_id = user_id;
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.goods_num = goods_num;
        this.goods_use = goods_use;
    }

    public UserPackInfo() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Integer getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(Integer goods_num) {
        this.goods_num = goods_num;
    }

    public Integer getGoods_use() {
        return goods_use;
    }

    public void setGoods_use(Integer goods_use) {
        this.goods_use = goods_use;
    }
    @Override
    public String toString() {
        return "UserPackInfo{" +
                "user_id='" + user_id + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", goods_name=" + goods_name +
                ", goods_num='" + goods_num + '\'' +
                ", goods_use=" + goods_use +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPackInfo that = (UserPackInfo) o;
        return Objects.equals(user_id, that.user_id) && Objects.equals(goods_id, that.goods_id) && Objects.equals(goods_name, that.goods_name) && Objects.equals(goods_num, that.goods_num) && Objects.equals(goods_use, that.goods_use);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, goods_id, goods_name, goods_num, goods_use);
    }
}
