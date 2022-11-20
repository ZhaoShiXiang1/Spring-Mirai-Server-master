package net.lz1998.pbbot.pojo;


import org.springframework.stereotype.Component;

@Component
public class Property {
    private Integer goods_id;
    private String goods_name;
    private Integer goods_num;
    private String goods_line;
    private Integer goods_use;

    @Override
    public String toString() {
        return "Property{" +
                "goods_id=" + goods_id +
                ", goods_name='" + goods_name + '\'' +
                ", goods_num=" + goods_num +
                ", goods_line='" + goods_line + '\'' +
                ", goods_use=" + goods_use +
                '}';
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

    public String getGoods_line() {
        return goods_line;
    }

    public void setGoods_line(String goods_line) {
        this.goods_line = goods_line;
    }

    public Integer getGoods_use() {
        return goods_use;
    }

    public void setGoods_use(Integer goods_use) {
        this.goods_use = goods_use;
    }

    public Property() {
    }

    public Property(Integer goods_id, String goods_name, Integer goods_num, String goods_line, Integer goods_use) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.goods_num = goods_num;
        this.goods_line = goods_line;
        this.goods_use = goods_use;
    }
}
