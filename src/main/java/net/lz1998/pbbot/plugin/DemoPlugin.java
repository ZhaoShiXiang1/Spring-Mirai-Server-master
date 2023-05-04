package net.lz1998.pbbot.plugin;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import net.lz1998.pbbot.pojo.UserInfo;
import net.lz1998.pbbot.pojo.UserPackInfo;
import net.lz1998.pbbot.utils.Msg;
import net.lz1998.pbbot.utis.ZXXUtil;
import okhttp3.*;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class DemoPlugin extends BotPlugin {


    private static OkHttpClient client = new OkHttpClient().newBuilder().build();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //日常功能
    //私发个人
    @Override
    public int onPrivateMessage(@NotNull Bot bot, @NotNull OnebotEvent.PrivateMessageEvent event) {
        long userId = event.getUserId();
        String text = event.getRawMessage();
        String str;
        String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Long eventime=event.getTime();
        jdbcTemplate.update("insert into `session` (`user_id`,`group_id`,`order`,`times`,`date_time`,`num`,`vip`) value (" + userId + ",9999999,\'"+text+"\',\'"+eventime+"\',\'"+datetime+"\',0,0)");
//        System.out.println(new Date().getTime());
        if (text.contains("吗")) {
            str = text;
            str = str.replace("吗", "");
            str = str.replace("?", "!");
            str = str.replace("? ", "! ");
            bot.sendPrivateMsg(userId, str, false);
            return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
        }
        if (text.equals("美女")) {
            // controller/ImageController.java
            // getImage(Long qq) 生成图片
            Msg msg = Msg.builder()
                    .image("https://api.btstu.cn/sjbz/api.php");
            bot.sendPrivateMsg(userId, msg, false);
//            System.out.println("-------------------------");
            return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
        }
        if (text.equals("写真美女")) {
            // controller/ImageController.java
            // getImage(Long qq) 生成图片
            Msg msg = Msg.builder()
                    .image("https://api.isoyu.com/mm_images.php");
            bot.sendPrivateMsg(userId, msg, false);
            return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
        }
        if (text.contains("舔狗日记")) {
            bot.sendPrivateMsg(userId, getDog(client), false);
            return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
        }
        if (text.contains("色图")) {
            bot.sendPrivateMsg(userId, "不可以色色", false);
            return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
        }
        if (text.contains("吃什么")) {
            bot.sendPrivateMsg(userId, getFood(client), false);
            return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
        }
        if (text.contains("秋名山飙车")) {
            bot.sendPrivateMsg(userId, "想啥呢，小伙子，你以为真有呀！", false);
            return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
        }
        if (text.contains("增加功能")||text.contains("反馈BUG")) {
          String words=  text.split(" ")[1];
//            System.out.println(words);
            bot.sendPrivateMsg(userId, "反馈成功，等待码农哥哥开发，亲", false);
           // String sql = "insert into `gamal_test` (`user_id`,`group_id`,`lines`) value (" + userId + ",9999999,"+"words"+")";
            jdbcTemplate.update("insert into `gamal_test` (`user_id`,`group_id`,`lines`,`times`,`date_time`) value (" + userId + ",9999999,\'"+words+"\',\'"+event.getTime()+"\',\'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\')");
            return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
        }
        if (text.contains("情话")) {
            String sweetWord = getSweetWord(client);
            bot.sendPrivateMsg(userId, sweetWord, false);
            return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
        }
        if (text.contains("风景照")) {
            // controller/ImageController.java
            // getImage(Long qq) 生成图片
            Msg msg = Msg.builder()
                    .image("https://api.1314.cool/bingimg");
            bot.sendPrivateMsg(userId, msg, false);
            return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
        }
        if (text.contains("星座运势")) {
            String[] split = text.split("\\s");
            String type = null;
            try {
                type = split[1];
            } catch (Exception e) {
                bot.sendPrivateMsg(userId, "请输入正确的格式： 星座运势 星座", false);
                e.printStackTrace();
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            if (type == null || "".equals(type)) {
                bot.sendPrivateMsg(userId, "请输入正确的格式： 星座运势 星座", false);
            } else {
                bot.sendPrivateMsg(userId, getState(client, type), false);
            }
            return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
        }
        if (text.contains("动漫图片")) {
            // controller/ImageController.java
            // getImage(Long qq) 生成图片
            Msg msg = Msg.builder()
                    .image("https://www.dmoe.cc/random.php");
            bot.sendPrivateMsg(userId, msg, false);
            return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
        }

        if (text.equals("注册")) {
            Integer integer = jdbcTemplate.queryForObject("SELECT count(*) FROM `user_info` where user_id =? ", Integer.class, userId);
            if (integer != 0) {
                bot.sendPrivateMsg(userId, "用户已注册，无需重复注册", false);
                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
            }
            int num = jdbcTemplate.update("insert into `user_info` (`user_id`,`user_create`,`date_time`,`animal_safe`) value (" + userId + ",\'"+event.getTime()+"\',\'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\',0)");
            int num2 = jdbcTemplate.update("insert into `user_animal_info` (`user_id`) value (" + userId + ")");

            List<Object[]> batchArgs = new ArrayList<Object[]>();
            batchArgs.add(new Object[]{0, "百宝箱", 2, 0});
            batchArgs.add(new Object[]{10, "宠物蛋", 2, 0});
            String sql = "insert into `user_pack_info` (user_id,goods_id,goods_name,goods_num,goods_use) values (" + userId + ",?,?,?,?)";
            jdbcTemplate.batchUpdate(sql, batchArgs);

            if (num == 1 && num2 == 1) {
                bot.sendPrivateMsg(userId, "用户注册成功,获得百宝箱2,宠物蛋2;使用方式输入->【使用+空格+道具名】", false);
                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
            } else {
                bot.sendPrivateMsg(userId, "用户注册失败，请重新输入【注册】", false);
                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
            }
        }
        //增加背包查询功能
        if (text.equals("背包")) {
            Integer integer = jdbcTemplate.queryForObject("SELECT count(*) FROM `user_info` where user_id =? ", Integer.class, userId);

            if (integer == 0) {
                bot.sendPrivateMsg(userId, "用户未注册请输入【注册】进行用户注册", false);
                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
            }
            // 访问数据库
            List<UserPackInfo> rides = jdbcTemplate.query("SELECT * FROM `user_pack_info` where user_id =? and goods_num>0", new RowMapper<UserPackInfo>() {
                @Override
                public UserPackInfo mapRow(ResultSet rs, int i) throws SQLException {
                    UserPackInfo userPackInfo = new UserPackInfo();
                    //  userPackInfo.setUser_id(rs.getLong("user_id"));
                    // userPackInfo.setGoods_id(rs.getInt("goods_id"));
                    userPackInfo.setGoods_name(rs.getString("goods_name"));
                    // userPackInfo.setGoods_use(rs.getInt("goods_use"));
                    userPackInfo.setGoods_num(rs.getInt("goods_num"));
                    return userPackInfo;
                }
            }, userId);
            StringBuffer stringBuffer = new StringBuffer();
            for (UserPackInfo tmp : rides) {
                stringBuffer.append(tmp.getGoods_name() + ": " + tmp.getGoods_num() + "\n");
            }
            bot.sendPrivateMsg(userId, stringBuffer.toString()+"\n使用方式@我输入->【使用+空格+道具名】", false);
            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin

        }
        //使用道具
        if (text.contains("使用 ")) {
            Integer integer = -1;//记录查询的数量
            Integer goods_use = -1;//记录道具的属性
            String[] goods = text.split(" ");
            if (goods[1] != null) {
                String goodName = goods[1];
                if (goods.length == 2) {
                    List<UserPackInfo> list = jdbcTemplate.query("SELECT * FROM `user_pack_info` where user_id =? and goods_name = ? ", new RowMapper<UserPackInfo>() {
                        @Override
                        public UserPackInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                            UserPackInfo userPackInfo = new UserPackInfo();
                            userPackInfo.setUser_id(rs.getLong("user_id"));
                            userPackInfo.setGoods_name(rs.getString("goods_name"));
                            userPackInfo.setGoods_num(rs.getInt("goods_num"));
                            userPackInfo.setGoods_use(rs.getInt("goods_use"));
                            return userPackInfo;
                        }
                    }, userId, goodName);
                    if (list.size()==0) {
                        bot.sendPrivateMsg(userId, "您的背包没有该物品", false);
                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                    } else {
                        UserPackInfo userPackInfo = list.get(0);
                        Integer integer1 = -1;
                        if (userPackInfo.getGoods_name().contains("百宝箱")) {
                            //调用方法获取道具放入背包
                            ArrayList<String> list1 = new ArrayList();
                            list1.add("01 初级增血剂 10");
                            list1.add("02 初级经验丹 100");
                            list1.add("03 中级增血剂 20");
                            list1.add("04 中级经验丹 200");
                            list1.add("05 高级增血剂 40");
                            list1.add("06 高级经验丹 400");
                            list1.add("07 大力丸 10");
                            list1.add("08 九转还魂丹 100");
                            list1.add("09 防护罩 1");
                            list1.add("10 宠物蛋 0");
                            //中奖信息放入数组中
                            List<Object[]> batchArgs = new ArrayList<Object[]>();
                            //抽奖坑货
                            for (int i = 0; i <= 5; i++) {
                                int num = (int) (Math.random() * 10);
                                if (num > 4) {
                                    num = (int) (Math.random() * 10);
                                    if (num > 4) {
                                        num = (int) (Math.random() * 10);
                                        if (num > 4) {
                                            num = (int) (Math.random() * 10);
                                            if (num == 9) {
                                                num = (int) (Math.random() * 10);
                                                batchArgs.add(new Object[]{list1.get(num).split(" ")[0], list1.get(num).split(" ")[1], list1.get(num).split(" ")[2]});
                                            } else {
                                                batchArgs.add(new Object[]{list1.get(num).split(" ")[0], list1.get(num).split(" ")[1], list1.get(num).split(" ")[2]});
                                            }
                                        } else {
                                            batchArgs.add(new Object[]{list1.get(num).split(" ")[0], list1.get(num).split(" ")[1], list1.get(num).split(" ")[2]});
                                        }
                                    } else {
                                        batchArgs.add(new Object[]{list1.get(num).split(" ")[0], list1.get(num).split(" ")[1], list1.get(num).split(" ")[2]});
                                    }
                                } else {
                                    batchArgs.add(new Object[]{list1.get(num).split(" ")[0], list1.get(num).split(" ")[1], list1.get(num).split(" ")[2]});
                                }
                            }
                            //放入背包中
                            String sql = "insert into `user_pack_info` (user_id,goods_id,goods_name,goods_num,goods_use) values (" + userId + ",?,?," + "1" + ",?)";
                            int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
                            //查询背包信息聚合后放回背包
                            List<UserPackInfo> goodsList = jdbcTemplate.query("SELECT user_id,goods_id,goods_name,goods_use,sum(goods_num) FROM `user_pack_info` where user_id =? group by user_id,goods_name,goods_id,goods_use", new RowMapper<UserPackInfo>() {
                                @Override
                                public UserPackInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                                    UserPackInfo userPackInfo = new UserPackInfo();
                                    userPackInfo.setUser_id(rs.getLong("user_id"));
                                    userPackInfo.setGoods_id(rs.getInt("goods_id"));
                                    userPackInfo.setGoods_name(rs.getString("goods_name"));
                                    userPackInfo.setGoods_use(rs.getInt("goods_use"));
                                    return userPackInfo;
                                }
                            }, userId);

                            for (UserPackInfo temp:goodsList) {
                                //查询数据库获取物品的数量,删除该物品的所有数据，将汇总后的数据放入表中
                                Integer integer5 = jdbcTemplate.queryForObject("SELECT sum(goods_num) FROM `user_pack_info` where user_id =? and goods_name=?", Integer.class, userId,temp.getGoods_name());
                                jdbcTemplate.update("delete from user_pack_info where user_id=? and goods_name=?", userId,temp.getGoods_name());
                                jdbcTemplate.update("insert into `user_pack_info` (user_id,goods_id,goods_name,goods_num,goods_use) values (" + userId + ",?,?,?,?)",temp.getGoods_id(),temp.getGoods_name(),integer5,temp.getGoods_use());
                            }
                            if (ints.length > 0) {
                                StringBuffer msg = new StringBuffer();
                                for (Object[] tmp : batchArgs) {
                                    msg.append(tmp[1] + " *1\n");
                                }
                                Integer update = jdbcTemplate.update("update user_pack_info set goods_num=? where user_id =? and goods_name = ?", userPackInfo.getGoods_num() - 1, userId, goodName);
                                if (update == 1) {
                                    bot.sendPrivateMsg(userId, "抽奖成功!!\n" + msg+"使用方式输入->使用+空格+道具名", false);
                                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                }
                            } else {
                                bot.sendPrivateMsg(userId, "抽奖失败请截图联系维护人员", false);
                                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                            }
                        } else {
                            String s = jdbcTemplate.queryForObject("SELECT animal_name FROM `user_info` where user_id =?", String.class, userId);
                            if (s == null) {
                                if (userPackInfo.getGoods_name().contains("宠物蛋")) {
                                    //使用宠物蛋召唤宠物
                                    //调用方法获取道具放入背包
                                    ArrayList<String> list1 = new ArrayList();
                                    list1.add("C级比翼鸟 100 10 10 0");
                                    list1.add("C级朱厌 100 10 10 0");
                                    list1.add("C级白泽 100 10 10 0");
                                    list1.add("C级黄鸟 100 10 10 0");
                                    list1.add("B级毕方 130 13 10 0");
                                    list1.add("B级穷奇 130 13 10 0");
                                    list1.add("B级应龙 130 13 10 0");
                                    list1.add("A级麒麟 150 15 10 0");
                                    list1.add("A级凤凰 150 15 10 0");
                                    list1.add("S级鲲 200 20 10 0");

                                    String animal = null;//存放宠物数据
                                    int num = (int) (Math.random() * 100);
                                    if (num < 84) {
                                        num = (int) (Math.random() * 4);
                                        animal = list1.get(num);
                                    } else if (num < 94) {
                                        num = (int) ((6 - 4 + 1) * Math.random() + 4);
                                        animal = list1.get(num);
                                    } else if (num < 99) {
                                        num = (int) ((8 - 7 + 1) * Math.random() + 7);
                                        animal = list1.get(num);
                                    }
                                    String[] s1 = animal.split(" ");
                                    String str1 = "update user_info set animal_name=?,animal_hp=?,animal_ce=?,animal_power=?,animal_level_count=? where user_id =?";
                                    integer1 = jdbcTemplate.update("update user_info set animal_name=?,animal_hp=?,animal_ce=?,animal_power=?,animal_level_count=? where user_id =?", s1[0], Integer.parseInt(s1[1]), Integer.parseInt(s1[2]), Integer.parseInt(s1[3]), Integer.parseInt(s1[4]), userId);
                                    if (integer1 == 1) {
                                        Integer update = jdbcTemplate.update("update user_pack_info set goods_num=? where user_id =? and goods_name = ?", userPackInfo.getGoods_num() - 1, userId, goodName);
                                        if (update == 1) {
                                            bot.sendPrivateMsg(userId, "宠物蛋使用成功，您的宠物为" + s1[0], false);
                                            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                        } else {
                                            bot.sendPrivateMsg(userId, "宠物蛋使用失败，请联系管理员", false);
                                            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                        }
                                    }
                                }
                                bot.sendPrivateMsg(userId, "您没有宠物，请使用宠物蛋", false);
                                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                            } else {
                                if (userPackInfo.getGoods_name().contains("增血")) {
                                    integer1 = jdbcTemplate.queryForObject("SELECT animal_hp FROM `user_info` where user_id =?", Integer.class, userPackInfo.getUser_id());
                                    if (integer1 < 0) {
                                        bot.sendPrivateMsg(userId, "您的宠物已阵亡，需使用九转还魂丹", false);
                                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                    } else {
                                        integer1 = integer1 + userPackInfo.getGoods_use();
                                       integer1 = jdbcTemplate.update("update user_info set animal_hp=? where user_id =?", integer1, userId);
                                    }
                                }
                                if (userPackInfo.getGoods_name().contains("九转还魂丹")) {
                                    integer1 = jdbcTemplate.queryForObject("SELECT animal_hp FROM `user_info` where user_id =? and animal_hp<0 ", Integer.class, userPackInfo.getUser_id());
                                    if (integer1 >= 0) {
                                        bot.sendPrivateMsg(userId, "您的宠物无生命危险，无需服用", false);
                                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                    } else {
                                        integer1 = jdbcTemplate.update("update user_info set animal_hp=100 where user_id =?", userId);
                                    }
                                }
                                if (userPackInfo.getGoods_name().contains("经验")) {
                                    integer1 = jdbcTemplate.queryForObject("SELECT animal_level_count FROM `user_info` where user_id =?", Integer.class, userPackInfo.getUser_id());
                                    integer1 = integer1 + userPackInfo.getGoods_use();
                                    integer1 = jdbcTemplate.update("update user_info set animal_level_count=? where user_id =?", integer1, userId);

                                }
                                if (userPackInfo.getGoods_name().contains("大力丸")) {
                                    integer1 = jdbcTemplate.queryForObject("SELECT animal_power FROM `user_info` where user_id =?", Integer.class, userPackInfo.getUser_id());
                                    integer1 = integer1 + userPackInfo.getGoods_use();
                                    integer1  = jdbcTemplate.update("update `user_info` set animal_power=? where user_id =?", integer1, userId);
                                }
                                if (userPackInfo.getGoods_name().contains("防护罩")) {
                                    integer1 = jdbcTemplate.queryForObject("SELECT animal_safe FROM `user_info` where user_id =?", Integer.class, userPackInfo.getUser_id());
                                    integer1 = integer1 + userPackInfo.getGoods_use();
                                    integer1 = jdbcTemplate.update("update `user_info` set animal_safe=1 where user_id =?", userId);

                                }
                                if (userPackInfo.getGoods_name().contains("宠物蛋")) {
                                    bot.sendPrivateMsg(userId, "您已经拥有宠物,请输入【放生 宠物名】,放生后即可使用", false);
                                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                }
                            }
                        }
                        //使用道具后触发结果
                        if (integer1 == 1) {
                            Integer update = jdbcTemplate.update("update user_pack_info set goods_num=? where user_id =? and goods_name = ?", userPackInfo.getGoods_num() - 1, userId, goodName);
//                            System.out.println("--->" + update);
                            if (update == 1) {
                                bot.sendPrivateMsg(userId, "道具" + userPackInfo.getGoods_name() + ": 1 使用完毕", false);
                                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                            } else {
                                bot.sendPrivateMsg(userId, "道具" + userPackInfo.getGoods_name() + ": 1 背包减少失败", false);
                                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                            }
                        }

                    }

                } else {
                    bot.sendPrivateMsg(userId, "输入语法有误请重新输入", false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                }
            }
            bot.sendPrivateMsg(userId, "输入语法有误请重新输入", false);
            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
        }
        if(text.equals("宠物排行榜")){
            List<UserInfo> redis = jdbcTemplate.query("SELECT * FROM `user_info` where animal_name is not null order by animal_hp desc limit 10", new RowMapper<UserInfo>() {
                @Override
                public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setUser_id(rs.getLong("user_id"));
                    userInfo.setAnimal_name(rs.getString("animal_name"));
                    userInfo.setAnimal_ce(rs.getInt("animal_ce"));
                    userInfo.setAnimal_hp(rs.getInt("animal_hp"));
                    userInfo.setAnimal_power(rs.getInt("animal_power"));
                    userInfo.setAnimal_level_count(rs.getInt("animal_level_count"));
                    return userInfo;
                }
            });
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("拥有者  :  宠物   :  血量\n");
            for (UserInfo tmp : redis) {
                stringBuffer.append(tmp.getUser_id()+":" + tmp.getAnimal_name() +":"+tmp.getAnimal_hp()+ "\n");
            }
            bot.sendPrivateMsg(userId, stringBuffer.toString(), false);
            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
        }
        if (text.contains("放生 ")){
            String[] lines = text.split(" ");
            if (lines.length==2){
               String animal_name =lines[1];
                Integer integer1 = jdbcTemplate.queryForObject("SELECT count(*) FROM `user_info` where user_id =? and animal_name = ?", Integer.class, userId,animal_name);
                if (integer1==1){
                    Integer update = jdbcTemplate.update("update `user_info` set animal_name=null,animal_hp=0,animal_ce=0,animal_power=0 where user_id =?",userId);
                    if (update==1){
                        bot.sendPrivateMsg(userId, "宠物放生成功", false);
                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                    }
                    bot.sendPrivateMsg(userId, "宠物放生失败", false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                }else {
                    bot.sendPrivateMsg(userId, "您目前暂无该宠物", false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                }
            }
            bot.sendPrivateMsg(userId, "您输入的语法错误请输入【放生+空格+宠物名】", false);
            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
        }

        //查询宠物信息
        if (text.equals("宠物信息")) {
            String animal_info = jdbcTemplate.queryForObject("SELECT animal_name FROM `user_info` where user_id =? ", String.class, userId);
            if (animal_info == null) {
                bot.sendPrivateMsg(userId, "您目前没有宠物，请使用宠物蛋", false);
                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
            } else {
                // 访问数据库
                List<UserInfo> redis = jdbcTemplate.query("SELECT * FROM `user_info` where user_id =?", new RowMapper<UserInfo>() {
                    @Override
                    public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
                        UserInfo userInfo = new UserInfo();
                        userInfo.setAnimal_name(rs.getString("animal_name"));
                        userInfo.setAnimal_ce(rs.getInt("animal_ce"));
                        userInfo.setAnimal_hp(rs.getInt("animal_hp"));
                        userInfo.setAnimal_power(rs.getInt("animal_power"));
                        userInfo.setAnimal_level_count(rs.getInt("animal_level_count"));
                        return userInfo;
                    }
                }, userId);
                StringBuffer stringBuffer = new StringBuffer();
                for (UserInfo tmp : redis) {
                    stringBuffer.append("宠物名称: " + tmp.getAnimal_name() + "\n");
                    stringBuffer.append("宠物血量: " + tmp.getAnimal_hp() + "\n");
                    stringBuffer.append("宠物攻击力: " + tmp.getAnimal_ce() + "\n");
                    stringBuffer.append("宠物体力: " + tmp.getAnimal_power() + "\n");
                    stringBuffer.append("宠物经验值: " + tmp.getAnimal_level_count());
                }
                bot.sendPrivateMsg(userId, stringBuffer.toString(), false);
                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
            }
        }
        //战斗模块
        if (text.contains("攻击")) {
            String[] lines = text.split(" ");
            if (lines.length == 2) {
                Long otherId = Long.parseLong(lines[1]);//攻击人的QQ号
                Integer integer1 = jdbcTemplate.queryForObject("SELECT count(*) FROM `user_info` where user_id =? ", Integer.class, userId);
                Integer integer2 = jdbcTemplate.queryForObject("SELECT count(*) FROM `user_info` where user_id =? ", Integer.class, otherId);
                if (integer1==0||integer2==0){
                    bot.sendPrivateMsg(userId, "双方中一方未进入游戏，请先注册", false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                }
                String integer3 = jdbcTemplate.queryForObject("SELECT count(animal_name) FROM `user_info` where user_id =? ", String.class, userId);
                String integer4 = jdbcTemplate.queryForObject("SELECT count(animal_name) FROM `user_info` where user_id =? ", String.class, otherId);
                Integer integer5 = jdbcTemplate.queryForObject("SELECT sum(animal_safe) FROM `user_info` where user_id =? ", Integer.class, otherId);
                Integer integer6 = jdbcTemplate.queryForObject("SELECT sum(animal_hp) FROM `user_info` where user_id =? ", Integer.class, userId);
                Integer integer7 = jdbcTemplate.queryForObject("SELECT sum(animal_hp) FROM `user_info` where user_id =? ", Integer.class, otherId);
                if (integer5==null){
                    bot.sendPrivateMsg(userId, "该用户未注册，请联系注册游戏", false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                }
                if (integer6<0){
                    bot.sendPrivateMsg(userId, "您的宠物已阵亡不能进行攻击，请使用九转还魂丹", false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                }
                if (integer7<0){
                    bot.sendPrivateMsg(userId, "对方的宠物已阵亡不能进行攻击", false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                }


                if (integer1 == 1 && integer2 == 1 && integer3 != null && integer4 != null&&integer5==0) {
                    //获取第一个用户
                    UserInfo user1 = jdbcTemplate.query("SELECT * FROM `user_info` where user_id =?", new RowMapper<UserInfo>() {
                        @Override
                        public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
                            UserInfo userInfo = new UserInfo();
                            userInfo.setAnimal_name(rs.getString("animal_name"));
                            userInfo.setAnimal_ce(rs.getInt("animal_ce"));
                            userInfo.setAnimal_hp(rs.getInt("animal_hp"));
                            userInfo.setAnimal_power(rs.getInt("animal_power"));
                            userInfo.setAnimal_level_count(rs.getInt("animal_level_count"));
                            return userInfo;
                        }
                    }, userId).get(0);
                    //获取第二个用户
                    UserInfo user2 = jdbcTemplate.query("SELECT * FROM `user_info` where user_id =?", new RowMapper<UserInfo>() {
                        @Override
                        public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
                            UserInfo userInfo = new UserInfo();
                            userInfo.setAnimal_name(rs.getString("animal_name"));
                            userInfo.setAnimal_ce(rs.getInt("animal_ce"));
                            userInfo.setAnimal_hp(rs.getInt("animal_hp"));
                            userInfo.setAnimal_power(rs.getInt("animal_power"));
                            userInfo.setAnimal_level_count(rs.getInt("animal_level_count"));
                            return userInfo;
                        }
                    }, otherId).get(0);
                    String resultUser = null;
                    Boolean flag = true;
                    //决斗判断
                    while (flag) {
                        user2.setAnimal_hp(user2.getAnimal_hp() - user1.getAnimal_ce());
                        user1.setAnimal_hp(user1.getAnimal_hp() - user2.getAnimal_ce());
                        if (user2.getAnimal_hp() < 0) {
                            user2.setAnimal_hp(-1);
                            user2.setOther_id(userId);
                            flag = false;
                            resultUser = "user1";
                        }
                        if (user1.getAnimal_hp() < 0) {
                            user1.setAnimal_hp(-1);
                            user1.setOther_id(otherId);
                            flag = false;
                            resultUser = "user2";
                        }
                    }
                    //更改数据库
                    Integer update1 = jdbcTemplate.update("update `user_info` set animal_hp=?,other_id=? where user_id =?", user1.getAnimal_hp(), user1.getOther_id(), userId);
                    Integer update2 = jdbcTemplate.update("update `user_info` set animal_hp=?,other_id=? where user_id =?", user2.getAnimal_hp(), user2.getOther_id(), otherId);
                    //准备回传信息
                    if (resultUser.equals("user1")) {
                        bot.sendPrivateMsg(userId, "恭喜您胜利了", false);
                        bot.sendPrivateMsg(otherId, "您的宠物被"+userId+"攻击了", false);
                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                    }
                    if (resultUser.equals("user2")) {
                        bot.sendPrivateMsg(userId, "很遗憾您失败了", false);
                        bot.sendPrivateMsg(otherId, "您的宠物被"+userId+"攻击了", false);
                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                    }
                } else {
                    bot.sendPrivateMsg(userId, "您输入的指令不正确请重新输入【攻击+空格+目标的QQ号码】", false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                }
            }
            bot.sendPrivateMsg(userId, "您输入的指令不正确请重新输入【攻击+空格+目标的QQ号码】", false);
            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
        }
        if (text.equals("宠物游戏")) {
            bot.sendPrivateMsg(userId, "欢迎使用宠物游戏,指令如下\n注册(宠物游戏注册)\n背包(获取背包信息)\n宠物信息(获取宠物信息)\n攻击+空格+目标QQ(进行宠物比拼)\n使用+空格+道具名称(使用道具)", false);
            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
        }
        if (text.equals("列表")){
            bot.sendPrivateMsg(userId,"功能尚不完善目前只支持以下功能\n" + getList(), false);
            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
        }
        //调用了多少次GPT
        //Integer num = jdbcTemplate.queryForObject("SELECT sum(num) FROM `session` where user_id =? ", Integer.class, userId);
        //Integer vip = jdbcTemplate.queryForObject("SELECT count(*) FROM `session` where user_id =? and vip=1", Integer.class, userId);
        //System.out.println(num+"----"+vip);
        //if (10>num || (vip!=0) ){
        bot.sendPrivateMsg(userId,  getGpt(text), false);
        jdbcTemplate.update("update `session` set `num`=1 where `user_id`="+userId+" and `group_id`=9999999 and `order`=\'"+text+"\' and `times`=\'"+eventime+"\'");
        return MESSAGE_IGNORE; // 当存在多个plugin时，继续执行下一个plugin
        //}
//        System.out.println(getGpt(text).toString());
        ///bot.sendPrivateMsg(userId, "您的询问次数今日已达上线,仅可以使用列表内功能\n,@我输入【列表】查看功能。打开空间扫码备注QQ号，1元即可解锁不限次数询问", false);
        //return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
    }

    //发送至群
    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        // 这里展示了RawMessage的用法（纯String）
        long groupId = event.getGroupId();
        String text = event.getRawMessage();
        String textGroup = text.replace("<at qq=\"2258507036\"/> ", "");
        System.out.println(textGroup);
        long userId = event.getUserId();
        String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Long eventime=event.getTime();
        //日常功能
        if (text.contains("<at qq=\"2258507036\"/>")) {
            jdbcTemplate.update("insert into `session` (`user_id`,`group_id`,`order`,`times`,`date_time`,`num`,`vip`) value (" + userId + ","+groupId+",\'"+textGroup+"\',\'"+event.getTime()+"\',\'"+datetime+"\',0,0)");
            if (text.contains("秋名山飙车")) {
                bot.sendGroupMsg(groupId, "想啥呢，小伙子，你以为真有呀！", false);
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            if (text.contains("色图")) {
                bot.sendGroupMsg(groupId, "不可以色色", false);
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            //价值百万的AI代码
            String str;
            if (text.contains("吗")) {
                str = textGroup;
                str = str.replace("吗", "");
                str = str.replace("?", "!");
                str = str.replace("? ", "! ");
                str = str.replace("<at qq=\"2258507036\"/> ", " ");
                bot.sendGroupMsg(groupId, str, false);
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            //菜单列表
            if (text.contains("列表")) {
                bot.sendGroupMsg(groupId, getList(), false);
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            if (text.contains("吃什么")) {
                bot.sendGroupMsg(groupId, getFood(client), false);
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            if (text.contains("情话")) {
                String sweetWord = getSweetWord(client);
                bot.sendGroupMsg(groupId, sweetWord, false);
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            if (text.contains("舔狗日记")) {
                bot.sendGroupMsg(groupId, getDog(client), false);
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            if (textGroup.equals("美女")) {
                // controller/ImageController.java
                // getImage(Long qq) 生成图片
                Msg.builder()
                        .image("https://api.btstu.cn/sjbz/api.php")
                        .sendToGroup(bot, groupId);
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            if (textGroup.contains("写真美女")) {
                // controller/ImageController.java
                // getImage(Long qq) 生成图片
                Msg.builder()
                        .image("https://api.isoyu.com/mm_images.php")
                        .sendToGroup(bot, groupId);
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            if (text.contains("风景照")) {
                // controller/ImageController.java
                // getImage(Long qq) 生成图片
                Msg.builder()
                        .image("https://api.1314.cool/bingimg")
                        .sendToGroup(bot, groupId);
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            if (text.contains("星座运势")) {
                String[] split = text.split("\\s");
                String type = null;
                try {
                    type = split[3];
                } catch (Exception e) {
                    bot.sendGroupMsg(groupId, "请输入正确的格式： 星座运势 星座", false);
                    e.printStackTrace();
                    return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
                }
                if (type == null || "".equals(type)) {
                    bot.sendGroupMsg(groupId, "请输入正确的格式： 星座运势 星座", false);
                } else {
                    bot.sendGroupMsg(groupId, getState(client, type), false);
                }
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            if (text.contains("动漫图片")) {
                // controller/ImageController.java
                // getImage(Long qq) 生成图片
                Msg.builder()
                        .image("https://www.dmoe.cc/random.php")
                        .sendToGroup(bot, groupId);
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            if (text.contains("增加功能")||text.contains("反馈BUG")) {
                jdbcTemplate.update("insert into `gamal_test` (`user_id`,`group_id`,`lines`,`times`,`date_time`) value (" + userId + ","+groupId+",\'"+textGroup.split(" ")[1].toString()+"\',\'"+event.getTime()+"\',\'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\')");
                bot.sendGroupMsg(groupId, "反馈成功，等待码农哥哥开发，亲", false);
                return MESSAGE_BLOCK; // 当存在多个plugin时，不执行下一个plugin
            }
            if(textGroup.equals("宠物排行榜")){
                List<UserInfo> redis = jdbcTemplate.query("SELECT * FROM `user_info`  where animal_name is not null order by animal_hp desc limit 10", new RowMapper<UserInfo>() {
                    @Override
                    public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
                        UserInfo userInfo = new UserInfo();
                        userInfo.setUser_id(rs.getLong("user_id"));
                        userInfo.setAnimal_name(rs.getString("animal_name"));
                        userInfo.setAnimal_ce(rs.getInt("animal_ce"));
                        userInfo.setAnimal_hp(rs.getInt("animal_hp"));
                        userInfo.setAnimal_power(rs.getInt("animal_power"));
                        userInfo.setAnimal_level_count(rs.getInt("animal_level_count"));
                        return userInfo;
                    }
                });

                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("拥有者  :  宠物   :  血量\n");
                for (UserInfo tmp : redis) {
                    stringBuffer.append(tmp.getUser_id()+":" + tmp.getAnimal_name() +":"+tmp.getAnimal_hp()+ "\n");
                }
                bot.sendGroupMsg(groupId, stringBuffer.toString(), false);
                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
            }
            if (textGroup.contains("放生 ")){
                String[] lines = textGroup.split(" ");
                if (lines.length==2){
                    String animal_name =lines[1];
                    Integer integer1 = jdbcTemplate.queryForObject("SELECT count(*) FROM `user_info` where user_id =? and animal_name = ?", Integer.class, userId,animal_name);
                    if (integer1==1){
                        Integer update = jdbcTemplate.update("update `user_info` set animal_name=null,animal_hp=0,animal_ce=0,animal_power=0 where user_id =?",userId);
                        if (update==1){
                            bot.sendGroupMsg(groupId, "宠物放生成功", false);
                            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                        }
                        bot.sendGroupMsg(groupId, "宠物放生失败", false);
                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                    }else {
                        bot.sendGroupMsg(groupId, "您目前暂无该宠物", false);
                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                    }
                }
                bot.sendGroupMsg(groupId, "您输入的语法错误请输入【放生+空格+宠物名】", false);
                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
            }
            if (textGroup.equals("注册")) {
                Integer integer = jdbcTemplate.queryForObject("SELECT count(*) FROM `user_info` where user_id =? ", Integer.class, userId);
                if (integer != 0) {
                    bot.sendGroupMsg(groupId, "用户已注册，无需重复注册", false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                }
                int num = jdbcTemplate.update("insert into `user_info` (`user_id`,`user_create`,`date_time`,`animal_safe`) value (" + userId + ",\'"+event.getTime()+"\',\'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\',0)");
                int num2 = jdbcTemplate.update("insert into `user_animal_info` (`user_id`) value (" + userId + ")");

                List<Object[]> batchArgs = new ArrayList<Object[]>();
                batchArgs.add(new Object[]{0, "百宝箱", 2, 0});
                batchArgs.add(new Object[]{10, "宠物蛋", 2, 0});
                String sql = "insert into `user_pack_info` (user_id,goods_id,goods_name,goods_num,goods_use) values (" + userId + ",?,?,?,?)";
                jdbcTemplate.batchUpdate(sql, batchArgs);

                if (num == 1 && num2 == 1) {
                    bot.sendGroupMsg(groupId, "用户注册成功,获得百宝箱2,宠物蛋2;使用方式@我输入->【使用+空格+道具名】", false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                } else {
                    bot.sendGroupMsg(groupId, "用户注册失败，请重新输入【注册】", false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                }
            }
            //增加背包查询功能
            if (textGroup.equals("背包")) {
                Integer integer = jdbcTemplate.queryForObject("SELECT count(*) FROM `user_info` where user_id =? ", Integer.class, userId);
                if (integer == 0) {
                    bot.sendGroupMsg(groupId, "用户未注册请输入【注册】进行用户注册", false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                }
                // 访问数据库
                List<UserPackInfo> rides = jdbcTemplate.query("SELECT * FROM `user_pack_info` where user_id =? and goods_num>0", new RowMapper<UserPackInfo>() {
                    @Override
                    public UserPackInfo mapRow(ResultSet rs, int i) throws SQLException {
                        UserPackInfo userPackInfo = new UserPackInfo();
                        //  userPackInfo.setUser_id(rs.getLong("user_id"));
                        // userPackInfo.setGoods_id(rs.getInt("goods_id"));
                        userPackInfo.setGoods_name(rs.getString("goods_name"));
                        // userPackInfo.setGoods_use(rs.getInt("goods_use"));
                        userPackInfo.setGoods_num(rs.getInt("goods_num"));
                        return userPackInfo;
                    }
                }, userId);
                StringBuffer stringBuffer = new StringBuffer();
                for (UserPackInfo tmp : rides) {
                    stringBuffer.append(tmp.getGoods_name() + ": " + tmp.getGoods_num() + "\n");
                }
                bot.sendGroupMsg(groupId, stringBuffer.toString()+"使用方式@我输入->【使用+空格+道具名】", false);
                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin

            }
            //使用道具
            if (textGroup.contains("使用 ")) {
                Integer integer = -1;//记录查询的数量
                Integer goods_use = -1;//记录道具的属性
                String[] goods = textGroup.split(" ");
                if (goods[1] != null) {
                    String goodName = goods[1];
                    if (goods.length == 2) {
                        List<UserPackInfo> list = jdbcTemplate.query("SELECT * FROM `user_pack_info` where user_id =? and goods_name = ? ", new RowMapper<UserPackInfo>() {
                            @Override
                            public UserPackInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                                UserPackInfo userPackInfo = new UserPackInfo();
                                userPackInfo.setUser_id(rs.getLong("user_id"));
                                userPackInfo.setGoods_name(rs.getString("goods_name"));
                                userPackInfo.setGoods_num(rs.getInt("goods_num"));
                                userPackInfo.setGoods_use(rs.getInt("goods_use"));
                                return userPackInfo;
                            }
                        }, userId, goodName);

                        if (list.size()==0) {
                            bot.sendGroupMsg(groupId, "您的背包没有该物品", false);
                            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                        } else {
                            UserPackInfo userPackInfo = list.get(0);
                            Integer integer1 = -1;
                            if (userPackInfo.getGoods_name().contains("百宝箱")) {
                                //调用方法获取道具放入背包
                                ArrayList<String> list1 = new ArrayList();
                                list1.add("01 初级增血剂 10");
                                list1.add("02 初级经验丹 100");
                                list1.add("03 中级增血剂 20");
                                list1.add("04 中级经验丹 200");
                                list1.add("05 高级增血剂 40");
                                list1.add("06 高级经验丹 400");
                                list1.add("07 大力丸 10");
                                list1.add("08 九转还魂丹 100");
                                list1.add("09 防护罩 1");
                                list1.add("10 宠物蛋 0");
                                //中奖信息放入数组中
                                List<Object[]> batchArgs = new ArrayList<Object[]>();
                                //抽奖坑货
                                for (int i = 0; i <= 5; i++) {
                                    int num = (int) (Math.random() * 10);
                                    if (num > 4) {
                                        num = (int) (Math.random() * 10);
                                        if (num > 4) {
                                            num = (int) (Math.random() * 10);
                                            if (num > 4) {
                                                num = (int) (Math.random() * 10);
                                                if (num == 9) {
                                                    num = (int) (Math.random() * 10);
                                                    batchArgs.add(new Object[]{list1.get(num).split(" ")[0], list1.get(num).split(" ")[1], list1.get(num).split(" ")[2]});
                                                } else {
                                                    batchArgs.add(new Object[]{list1.get(num).split(" ")[0], list1.get(num).split(" ")[1], list1.get(num).split(" ")[2]});
                                                }
                                            } else {
                                                batchArgs.add(new Object[]{list1.get(num).split(" ")[0], list1.get(num).split(" ")[1], list1.get(num).split(" ")[2]});
                                            }
                                        } else {
                                            batchArgs.add(new Object[]{list1.get(num).split(" ")[0], list1.get(num).split(" ")[1], list1.get(num).split(" ")[2]});
                                        }
                                    } else {
                                        batchArgs.add(new Object[]{list1.get(num).split(" ")[0], list1.get(num).split(" ")[1], list1.get(num).split(" ")[2]});
                                    }
                                }
                                //放入背包中
                                String sql = "insert into `user_pack_info` (user_id,goods_id,goods_name,goods_num,goods_use) values (" + userId + ",?,?," + "1" + ",?)";
                                int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
                                //查询背包信息聚合后放回背包
                                List<UserPackInfo> goodsList = jdbcTemplate.query("SELECT user_id,goods_id,goods_name,goods_use,sum(goods_num) FROM `user_pack_info` where user_id =? group by user_id,goods_name,goods_id,goods_use", new RowMapper<UserPackInfo>() {
                                    @Override
                                    public UserPackInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                                        UserPackInfo userPackInfo = new UserPackInfo();
                                        userPackInfo.setUser_id(rs.getLong("user_id"));
                                        userPackInfo.setGoods_id(rs.getInt("goods_id"));
                                        userPackInfo.setGoods_name(rs.getString("goods_name"));
                                        userPackInfo.setGoods_use(rs.getInt("goods_use"));
                                        return userPackInfo;
                                    }
                                }, userId);

                                for (UserPackInfo temp:goodsList) {
                                    //查询数据库获取物品的数量,删除该物品的所有数据，将汇总后的数据放入表中
                                    Integer integer5 = jdbcTemplate.queryForObject("SELECT sum(goods_num) FROM `user_pack_info` where user_id =? and goods_name=?", Integer.class, userId,temp.getGoods_name());
                                    jdbcTemplate.update("delete from user_pack_info where user_id=? and goods_name=?", userId,temp.getGoods_name());
                                    jdbcTemplate.update("insert into `user_pack_info` (user_id,goods_id,goods_name,goods_num,goods_use) values (" + userId + ",?,?,?,?)",temp.getGoods_id(),temp.getGoods_name(),integer5,temp.getGoods_use());
                                }
                                if (ints.length > 0) {
                                    StringBuffer msg = new StringBuffer();
                                    for (Object[] tmp : batchArgs) {
                                        msg.append(tmp[1] + " *1\n");
                                    }
                                    Integer update = jdbcTemplate.update("update user_pack_info set goods_num=? where user_id =? and goods_name = ?", userPackInfo.getGoods_num() - 1, userId, goodName);
                                    if (update == 1) {
                                        bot.sendGroupMsg(groupId, "抽奖成功!!\n" + msg+"\n使用方式@我输入->【使用+空格+道具名】", false);
                                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                    }
                                } else {
                                    bot.sendGroupMsg(groupId, "抽奖失败请截图联系维护人员", false);
                                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                }
                            } else {
                                String s = jdbcTemplate.queryForObject("SELECT animal_name FROM `user_info` where user_id =?", String.class, userId);
                                if (s == null) {
                                    if (userPackInfo.getGoods_name().contains("宠物蛋")) {
                                        //使用宠物蛋召唤宠物
                                        //调用方法获取道具放入背包
                                        ArrayList<String> list1 = new ArrayList();
                                        list1.add("C级比翼鸟 100 10 10 0");
                                        list1.add("C级朱厌 100 10 10 0");
                                        list1.add("C级白泽 100 10 10 0");
                                        list1.add("C级黄鸟 100 10 10 0");
                                        list1.add("B级毕方 130 13 10 0");
                                        list1.add("B级穷奇 130 13 10 0");
                                        list1.add("B级应龙 130 13 10 0");
                                        list1.add("A级麒麟 150 15 10 0");
                                        list1.add("A级凤凰 150 15 10 0");
                                        list1.add("S级鲲 200 20 10 0");

                                        String animal = null;//存放宠物数据
                                        int num = (int) (Math.random() * 100);
                                        if (num < 84) {
                                            num = (int) (Math.random() * 4);
                                            animal = list1.get(num);
                                        } else if (num < 94) {
                                            num = (int) ((6 - 4 + 1) * Math.random() + 4);
                                            animal = list1.get(num);
                                        } else if (num < 99) {
                                            num = (int) ((8 - 7 + 1) * Math.random() + 7);
                                            animal = list1.get(num);
                                        }
                                        String[] s1 = animal.split(" ");
                                        integer1 = jdbcTemplate.update("update user_info set animal_name=?,animal_hp=?,animal_ce=?,animal_power=?,animal_level_count=? where user_id =?", s1[0], Integer.parseInt(s1[1]), Integer.parseInt(s1[2]), Integer.parseInt(s1[3]), Integer.parseInt(s1[4]), userId);
                                        if (integer1 == 1) {
                                            Integer update = jdbcTemplate.update("update user_pack_info set goods_num=? where user_id =? and goods_name = ?", userPackInfo.getGoods_num() - 1, userId, goodName);
                                            if (update == 1) {
                                                bot.sendGroupMsg(groupId, "宠物蛋使用成功，您的宠物为" + s1[0], false);
                                                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                            } else {
                                                bot.sendGroupMsg(groupId, "宠物蛋使用失败，请联系管理员", false);
                                                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                            }
                                        }
                                    }
                                    bot.sendGroupMsg(groupId, "您没有宠物，请使用宠物蛋", false);
                                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                } else {
                                    if (userPackInfo.getGoods_name().contains("增血")) {
                                        integer1 = jdbcTemplate.queryForObject("SELECT animal_hp FROM `user_info` where user_id =?", Integer.class, userPackInfo.getUser_id());
                                        if (integer1 < 0) {
                                            bot.sendGroupMsg(groupId, "您的宠物已阵亡，需使用九转还魂丹", false);
                                            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                        } else {
                                            integer1 = integer1 + userPackInfo.getGoods_use();
                                            integer1 = jdbcTemplate.update("update user_info set animal_hp=? where user_id =?", integer1, userId);
                                        }
                                    }
                                    if (userPackInfo.getGoods_name().contains("九转还魂丹")) {
                                        integer1 = jdbcTemplate.queryForObject("SELECT animal_hp FROM `user_info` where user_id =? and animal_hp<0 ", Integer.class, userPackInfo.getUser_id());
                                        if (integer1 >= 0) {
                                            bot.sendGroupMsg(groupId, "您的宠物无生命危险，无需服用", false);
                                            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                        } else {
                                           integer1 = jdbcTemplate.update("update user_info set animal_hp=100 where user_id =?", userId);
                                        }
                                    }
                                    if (userPackInfo.getGoods_name().contains("经验")) {
                                        integer1 = jdbcTemplate.queryForObject("SELECT animal_level_count FROM `user_info` where user_id =?", Integer.class, userPackInfo.getUser_id());
                                        integer1 = integer1 + userPackInfo.getGoods_use();
                                        integer1 = jdbcTemplate.update("update user_info set animal_level_count=? where user_id =?", integer1, userId);
                                    }
                                    if (userPackInfo.getGoods_name().contains("大力丸")) {
                                        integer1 = jdbcTemplate.queryForObject("SELECT animal_power FROM `user_info` where user_id =?", Integer.class, userPackInfo.getUser_id());
                                        integer1 = integer1 + userPackInfo.getGoods_use();
                                        integer1 = jdbcTemplate.update("update `user_info` set animal_power=? where user_id =?", integer1, userId);
                                    }
                                    if (userPackInfo.getGoods_name().contains("防护罩")) {
                                        integer1 = jdbcTemplate.queryForObject("SELECT animal_safe FROM `user_info` where user_id =?", Integer.class, userPackInfo.getUser_id());
                                        integer1 = integer1 + userPackInfo.getGoods_use();
                                        integer1 = jdbcTemplate.update("update `user_info` set animal_safe=1 where user_id =?", userId);
                                    }
                                    if (userPackInfo.getGoods_name().contains("宠物蛋")) {
                                        bot.sendGroupMsg(groupId, "您已经拥有宠物,请输入【放生 宠物名】,放生后即可使用", false);
                                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                    }
                                }
                            }
                            //使用道具后触发结果
                            if (integer1 == 1) {
                                Integer update = jdbcTemplate.update("update user_pack_info set goods_num=? where user_id =? and goods_name = ?", userPackInfo.getGoods_num() - 1, userId, goodName);
//                                System.out.println("--->" + update);
                                if (update == 1) {
                                    bot.sendGroupMsg(groupId, "道具" + userPackInfo.getGoods_name() + ": 1 使用完毕", false);
                                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                } else {
                                    bot.sendGroupMsg(groupId, "道具" + userPackInfo.getGoods_name() + ": 1 背包减少失败", false);
                                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                                }
                            }

                        }

                    } else {
                        bot.sendGroupMsg(groupId, "输入语法有误请重新输入", false);
                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                    }
                }
                bot.sendGroupMsg(groupId, "输入语法有误请重新输入", false);
                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
            }
            //查询宠物信息
            if (textGroup.equals("宠物信息")) {
                String animal_info = jdbcTemplate.queryForObject("SELECT animal_name FROM `user_info` where user_id =? ", String.class, userId);
                if (animal_info == null) {
                    bot.sendGroupMsg(groupId, "您目前没有宠物,请使用宠物蛋", false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                } else {
                    // 访问数据库
                    List<UserInfo> redis = jdbcTemplate.query("SELECT * FROM `user_info` where user_id =?", new RowMapper<UserInfo>() {
                        @Override
                        public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
                            UserInfo userInfo = new UserInfo();
                            userInfo.setAnimal_name(rs.getString("animal_name"));
                            userInfo.setAnimal_ce(rs.getInt("animal_ce"));
                            userInfo.setAnimal_hp(rs.getInt("animal_hp"));
                            userInfo.setAnimal_power(rs.getInt("animal_power"));
                            userInfo.setAnimal_level_count(rs.getInt("animal_level_count"));
                            return userInfo;
                        }
                    }, userId);
                    StringBuffer stringBuffer = new StringBuffer();
                    for (UserInfo tmp : redis) {
                        stringBuffer.append("宠物名称: " + tmp.getAnimal_name() + "\n");
                        stringBuffer.append("宠物血量: " + tmp.getAnimal_hp() + "\n");
                        stringBuffer.append("宠物攻击力: " + tmp.getAnimal_ce() + "\n");
                        stringBuffer.append("宠物体力: " + tmp.getAnimal_power() + "\n");
                        stringBuffer.append("宠物经验值: " + tmp.getAnimal_level_count());
                    }
                    bot.sendGroupMsg(groupId, stringBuffer.toString(), false);
                    return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                }
            }
            //战斗模块
            if (textGroup.contains("攻击")) {
                String[] lines = textGroup.split(" ");
                if (lines.length == 2) {
                    Long otherId = Long.parseLong(lines[1]);//攻击人的QQ号
                    Integer integer1 = jdbcTemplate.queryForObject("SELECT count(*) FROM `user_info` where user_id =? ", Integer.class, userId);
                    Integer integer2 = jdbcTemplate.queryForObject("SELECT count(*) FROM `user_info` where user_id =? ", Integer.class, otherId);
                    String integer3 = jdbcTemplate.queryForObject("SELECT count(animal_name) FROM `user_info` where user_id =? ", String.class, userId);
                    String integer4 = jdbcTemplate.queryForObject("SELECT count(animal_name) FROM `user_info` where user_id =? ", String.class, otherId);
                    Integer integer5 = jdbcTemplate.queryForObject("SELECT sum(animal_safe) FROM `user_info` where user_id =? ", Integer.class, otherId);
                    Integer integer6 = jdbcTemplate.queryForObject("SELECT sum(animal_hp) FROM `user_info` where user_id =? ", Integer.class, userId);
                    Integer integer7 = jdbcTemplate.queryForObject("SELECT sum(animal_hp) FROM `user_info` where user_id =? ", Integer.class, otherId);
                    if (integer5==null){
                        bot.sendGroupMsg(groupId, "该用户未注册，请联系注册游戏", false);
                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                    }
                    if (integer6<0){
                        bot.sendGroupMsg(groupId, "您的宠物已阵亡不能进行攻击，请使用九转还魂丹", false);
                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                    }
                    if (integer7<0){
                        bot.sendGroupMsg(groupId, "对方的宠物已阵亡不能进行攻击", false);
                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                    }
                    if (integer1 == 1 && integer2 == 1 && integer3 != null && integer4 != null&&integer5==0) {
                        //获取第一个用户
                        UserInfo user1 = jdbcTemplate.query("SELECT * FROM `user_info` where user_id =?", new RowMapper<UserInfo>() {
                            @Override
                            public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
                                UserInfo userInfo = new UserInfo();
                                userInfo.setAnimal_name(rs.getString("animal_name"));
                                userInfo.setAnimal_ce(rs.getInt("animal_ce"));
                                userInfo.setAnimal_hp(rs.getInt("animal_hp"));
                                userInfo.setAnimal_power(rs.getInt("animal_power"));
                                userInfo.setAnimal_level_count(rs.getInt("animal_level_count"));
                                return userInfo;
                            }
                        }, userId).get(0);
                        //获取第二个用户
                        UserInfo user2 = jdbcTemplate.query("SELECT * FROM `user_info` where user_id =?", new RowMapper<UserInfo>() {
                            @Override
                            public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
                                UserInfo userInfo = new UserInfo();
                                userInfo.setAnimal_name(rs.getString("animal_name"));
                                userInfo.setAnimal_ce(rs.getInt("animal_ce"));
                                userInfo.setAnimal_hp(rs.getInt("animal_hp"));
                                userInfo.setAnimal_power(rs.getInt("animal_power"));
                                userInfo.setAnimal_level_count(rs.getInt("animal_level_count"));
                                return userInfo;
                            }
                        }, otherId).get(0);
                        String resultUser = null;
                        Boolean flag = true;
                        //决斗判断
                        while (flag) {
                            user2.setAnimal_hp(user2.getAnimal_hp() - user1.getAnimal_ce());
                            user1.setAnimal_hp(user1.getAnimal_hp() - user2.getAnimal_ce());
                            if (user2.getAnimal_hp() < 0) {
                                user2.setAnimal_hp(-1);
                                user2.setOther_id(userId);
                                flag = false;
                                resultUser = "user1";
                            }
                            if (user1.getAnimal_hp() < 0) {
                                user1.setAnimal_hp(-1);
                                user1.setOther_id(otherId);
                                flag = false;
                                resultUser = "user2";
                            }
                        }
                        //更改数据库
                        Integer update1 = jdbcTemplate.update("update `user_info` set animal_hp=?,other_id=? where user_id =?", user1.getAnimal_hp(), user1.getOther_id(), userId);
                        Integer update2 = jdbcTemplate.update("update `user_info` set animal_hp=?,other_id=? where user_id =?", user2.getAnimal_hp(), user2.getOther_id(), otherId);
                        //准备回传信息
                        if (resultUser.equals("user1")) {
                            bot.sendGroupMsg(groupId, "恭喜您胜利了", false);
                            bot.sendPrivateMsg(otherId, "您的宠物被"+userId+"攻击了", false);
                            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                        }
                        if (resultUser.equals("user2")) {
                            bot.sendGroupMsg(groupId, "很遗憾您失败了", false);
                            bot.sendPrivateMsg(otherId, "您的宠物被"+userId+"攻击了", false);
                            return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                        }
                    } else {
                        bot.sendGroupMsg(groupId, "对方处于保护期,暂时无法攻击", false);
                        return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
                    }
                }
                bot.sendGroupMsg(groupId, "您输入的指令不正确请重新输入【攻击+空格+目标的QQ号码】", false);
                return MESSAGE_BLOCK;// 当存在多个plugin时，继续执行下一个plugin
            }
            if (textGroup.equals("宠物游戏")) {
                bot.sendGroupMsg(groupId, "欢迎使用宠物游戏,指令如下\n注册(宠物游戏注册)\n背包(获取背包信息)\n宠物信息(获取宠物信息)\n攻击+空格+目标QQ(进行宠物比拼)\n使用+空格+道具名称(使用道具)", false);
                return MESSAGE_IGNORE; // 当存在多个plugin时，继续执行下一个plugin
            }
            if (textGroup.equals("列表")){
                bot.sendGroupMsg(groupId, "功能尚不完善目前只支持以下功能\n" + getList(), false);
                return MESSAGE_IGNORE; // 当存在多个plugin时，继续执行下一个plugin
            }
            //调用了多少次GPT
           // Integer num = jdbcTemplate.queryForObject("SELECT sum(num) FROM `session` where user_id =? ", Integer.class, userId);
          //  Integer vip = jdbcTemplate.queryForObject("SELECT count(*) FROM `session` where user_id =? and vip=1", Integer.class, userId);
           // if (10>num || (vip!=0) ){
            bot.sendGroupMsg(groupId,  getGpt(text).toString(), false);
            jdbcTemplate.update("update `session` set `num`=1 where `user_id`="+userId+" and `group_id`="+groupId+" and `order`=\'"+textGroup+"\' and `times`=\'"+eventime+"\'");
            return MESSAGE_IGNORE; // 当存在多个plugin时，继续执行下一个plugin
           // }

            //bot.sendGroupMsg(groupId,"您的询问次数今日已达上线,仅可以使用列表内功能\n,@我输入【列表】查看功能。打开空间扫码备注QQ号，1元即可解锁不限次数询问", false);

        }
//        bot.sendGroupMsg(groupId,  getGpt(text).toString(), false);
        return MESSAGE_IGNORE; // 当存在多个plugin时，继续执行下一个plugin
    }

    //列表
    public static String getList() {

        String result =
                "您可以@我输入:" + "\n" +
                        "列表(获取所有功能)\n"
                        +"美女/色图/情话/风景照\n"
                        + "宠物游戏" + "\n"
                        + "动漫图片" + "\n"
                        + "舔狗日记(有上限)" + "\n"
                        + "今天吃什么" + "\n"
                        + "秋名山飙车" + "\n"
                        + "星座运势 xx座" + "\n"
                        + "增加功能/反馈BUG+空格+想增加的内容/问题" + "\n"
                        + "【添加好友即可使用单独会话功能】" ;
        return result;
    }

    //讲情话（免费）
    public static String getSweetWord(OkHttpClient client) {
        try {
            Request request = new Request.Builder()
                    .url("https://api.1314.cool/words/api.php")
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string().replaceAll("<br>", "\n");
        } catch (Exception e) {
            return "出错了";
        }
    }

    //吃什么（apispace）
    public static String getFood(OkHttpClient client) {
        try {

            Request request = new Request.Builder()
                    .url("https://eolink.o.apispace.com/eat222/api/v1/forward/chishenme?size=1")
                    .method("GET", null)
                    .addHeader("X-APISpace-Token", "t2a8yuh3czr9p518nnge2kjzhxtgh9fh")
                    .addHeader("Authorization-Type", "apikey")
                    .build();

            Response response = client.newCall(request).execute();

            String json = response.body() != null ? response.body().string() : "";
            if ("".equals(json)) {
                return "吃屁";
            }
            JSONObject jsonObject = JSONObject.parseObject(json);

            JSONArray data = jsonObject.getJSONArray("data");
            String food = data.getString(0);
            return food;
        } catch (Exception e) {
            return "出错了";
        }
    }

    //星座运势(免费)
    public static String getState(OkHttpClient client, String type) {
        try {
            String change = ZXXUtil.change(type);

            Request request = new Request.Builder()
                    .url("http://web.juhe.cn/constellation/getAll?consName=" + change + "&type=today&key=d6b522412c87540a631c1f2a0d119aef")
                    .method("GET", null)
                    .build();

            Response response = client.newCall(request).execute();

            String string = response.body().string();

            JSONObject jsonObject = JSONObject.parseObject(string);

            String datetime = jsonObject.getString("datetime");
            String name = jsonObject.getString("name");
            String QFriend = jsonObject.getString("QFriend");
            String color = jsonObject.getString("color");
            String health = jsonObject.getString("health");
            String love = jsonObject.getString("love");
            String work = jsonObject.getString("work");
            String money = jsonObject.getString("money");
            String number = jsonObject.getString("number");
            String all = jsonObject.getString("all");
            String summary = jsonObject.getString("summary");

            String result = "今日运势:" + name + "\n"
                    + datetime + "\n"
                    + "综合运势(100分):" + all + "\n"
                    + "爱情运势(100分):" + love + "\n"
                    + "学业工作(100分):" + work + "\n"
                    + "健康运势(100分):" + health + "\n"
                    + "财富运势(100分):" + money + "\n"
                    + "幸运色:" + color + "\n"
                    + "幸运数字:" + number + "\n"
                    + "速配星座:" + QFriend + "\n"
                    + "今日运势:" + summary + "\n";

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "出错了";
        }
    }

    //舔狗日记(次数限制)
    public static String getDog(OkHttpClient client)  {
        try {
            Request request = new Request.Builder()
                    .url("https://v.api.aa1.cn/api/tiangou/index.php")
                    .method("GET", null)
                    .build();

            Response response = client.newCall(request).execute();
            String str = response.body().string();
            String substring = str.replace("1","");
//            System.out.println(substring);
            JSONObject jsonObject = JSONObject.parseObject(substring);

            JSONArray newslist = jsonObject.getJSONArray("newslist");
            JSONObject jsonObject1 = newslist.getJSONObject(0);
            return jsonObject1.getString("content");
        } catch (Exception e) {
            Request request1 = new Request.Builder()
                    .url("https://api.oick.cn/dog/api.php")
                    .method("GET", null)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request1).execute();
                String str = response.body().string();
                return str;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return "出错了";
        }
    }
    //舔狗日记不限次数
    public static String getGpt(String text){
      String str3 = null;
      String  text2=text.replace("<at qq=\"2258507036\"/> ","");
      StringBuffer buffer = new StringBuffer();
        // TODO Auto-generated method stub
      Process proc;
//      String str2="python C:\\Users\\PC\\IdeaProjects\\GPT\\gpt1.0\\test1.py "+text2;

       String str2="python C:\\Users\\Administrator\\Desktop\\test1.py "+text2;
      System.out.println("str2="+str2);
        try {
            proc = Runtime.getRuntime().exec(str2);// 执行py文件
            //用输入输出流来截取结果

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            buffer.append("你好:");
//            System.out.println(in.readLine());
            while ((line = in.readLine()) != null) {
//                System.out.println("进入循环");
//                System.out.println(line);
                buffer.append('\n');
                buffer.append(line.toString());

            }
//            System.out.println("结束");
            in.close();
            proc.waitFor();
//            System.out.println("-------------");
      str3= buffer.toString().replace("\n\n","\n");//替换为空古诗会出现异常
      System.out.println(str3);
      return str3;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return str3;
    }


}
