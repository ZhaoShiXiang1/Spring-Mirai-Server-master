package net.lz1998.pbbot.test;

import net.lz1998.pbbot.pojo.UserPackInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

//此类为垃圾回收类，存放暂时不用的代码
public class test {
    //讲笑话（付费）
//    public static List<String> getJokesByRandom(OkHttpClient client) {
//        try {
//            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//            RequestBody body = RequestBody.create(mediaType, "pageSize=2");
//            Request request = new Request.Builder()
//                    .url("https://eolink.o.apispace.com/xhdq/common/joke/getJokesByRandom")
//                    .method("POST", body)
//                    .addHeader("X-APISpace-Token", "t2a8yuh3czr9p518nnge2kjzhxtgh9fh")
//                    .addHeader("Authorization-Type", "apikey")
//                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
//                    .build();
//
//            Response response = client.newCall(request).execute();
//
//            String json = response.body().string();
//
//            JSONObject jsonObject = JSONObject.parseObject(json);
//
//            JSONArray jsonArray = jsonObject.getJSONArray("result");
//
//            ArrayList<String> list = new ArrayList<>();
//
//
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JSONObject joke = jsonArray.getJSONObject(i);
//                list.add(joke.getString("content"));
//            }
//
//            return list;
//        } catch (Exception e) {
//            return new ArrayList<String>() {
//            };
//        }
//    }

    //讲笑话（免费）
//    public static String getJoke(OkHttpClient client) {
//        try {
//            Request request = new Request.Builder()
//                    .url("https://api.vvhan.com/api/joke")
//                    .method("GET", null)
//                    .build();
//
//            Response response = client.newCall(request).execute();
//            return response.body().string();
//        } catch (Exception e) {
//            return "出错了";
//        }
//    }

    //查天气（免费）
//    public static String getWeather(OkHttpClient client) {
//        try {
//            Request request = new Request.Builder()
//                    .url("https://api.vvhan.com/api/weather")
//                    .method("GET", null)
//                    .build();
//
//            Response response = client.newCall(request).execute();
//            String json = response.body().string();
//
//            JSONObject jsonObject = JSONObject.parseObject(json);
//
//            String city = jsonObject.getString("city");
//            JSONObject info = jsonObject.getJSONObject("info");
//            String date = info.getString("date");
//            String week = info.getString("week");
//            String type = info.getString("type");
//            String high = info.getString("high");
//            String low = info.getString("low");
//            String fengxiang = info.getString("fengxiang");
//            String fengli = info.getString("fengli");
//            String tip = info.getString("tip");
//
//            return "今日天气 \n"
//                    + city + "\n"
//                    + date + " " + week + "\n"
//                    + type + "\n"
//                    + high + " " + low + "\n"
//                    + fengxiang + " " + fengli + "\n"
//                    + tip + "\n"
//                    ;
//        } catch (Exception e) {
//            return "出错了";
//        }
//    }
    //星座运势(免费)
//    public static String getState2(OkHttpClient client, String type) {
//        try {
//            String change = ZXXUtil.change(type);
//
//            Request request = new Request.Builder()
//                    .url("https://api.vvhan.com/api/horoscope?type=" + change + "&time=today")
//                    .method("GET", null)
//                    .build();
//
//            Response response = client.newCall(request).execute();
//
//            String string = response.body().string();
//
//            JSONObject json = JSONObject.parseObject(string);
//
//            JSONObject jsonObject = json.getJSONObject("data");
//
//            String title = jsonObject.getString("title");
//            String time = jsonObject.getString("time");
//
//            JSONObject fortune = jsonObject.getJSONObject("fortune");
//            String all = fortune.getString("all");
//            String love = fortune.getString("love");
//            String work = fortune.getString("work");
//            String money = fortune.getString("money");
//            String luckycolor = jsonObject.getString("luckycolor");
//            String luckynumber = jsonObject.getString("luckynumber");
//            String luckyconstellation = jsonObject.getString("luckyconstellation");
//
//
//            JSONObject fortunetext = jsonObject.getJSONObject("fortunetext");
//            String all1 = fortunetext.getString("all");
//            String love1 = fortunetext.getString("love");
//            String work1 = fortunetext.getString("work");
//            String money1 = fortunetext.getString("money");
//            String health = fortunetext.getString("health");
//
//
//            String result = title + "\n"
//                    + time + "\n"
//                    + "综合运势(5分):" + all + "\n"
//                    + "爱情运势(5分):" + love + "\n"
//                    + "学业工作(5分):" + work + "\n"
//                    + "财富运势(5分):" + money + "\n"
//                    + "幸运色:" + luckycolor + "\n"
//                    + "幸运数字:" + luckynumber + "\n"
//                    + "速配星座:" + luckyconstellation + "\n"
//                    + "综合运势:" + all1 + "\n"
//                    + "爱情运势:" + love1 + "\n"
//                    + "学业工作:" + work1 + "\n"
//                    + "财富运势:" + money1 + "\n"
//                    + "健康运势:" + health + "\n";
//
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "出错了";
//        }
//    }
    //福利(免费)
//    public static String getPhotoUrl(OkHttpClient client) {
//        try {
//            Request request = new Request.Builder()
//                    .url("https://api.vvhan.com/api/mobil.girl?type=json")
//                    .method("GET", null)
//                    .build();
//
//            Response response = client.newCall(request).execute();
//
//            String string = response.body().string();
//
//            JSONObject jsonObject = JSONObject.parseObject(string);
//
//            String imgurl = jsonObject.getString("imgurl");
//
//            //System.out.println(imgurl);
//
//            return imgurl.replaceAll("\\\\", "");
//
//
//        } catch (Exception e) {
//            return "出错了";
//        }
//    }

    //游戏部分
//    public static String getGame(String text, Long userId, String groupId, JdbcTemplate jdbcTemplate){
//        System.out.println("测试开始");
//        Integer num = jdbcTemplate.queryForObject("SELECT count(*) FROM `user_info` where user_id = ? ", Integer.class, userId);
//        System.out.println(num);
//        //查询用户信息
////        if(num==0){
////            return "用户不存在，请输入【注册】";
////        }
//        if (text.equals("注册")){
//            int updateRow = jdbcTemplate.update("insert into `user_info` set('user_id','user_create') values(?,?)",userId,new Date());
//            if (updateRow==1){
//                return "用户注册成功！！！";
//            }else {
//                return "用户失败，请重新注册";
//            }
//        }
//        //背包信息查询
//        if ("背包".equals(text)){
//            // 访问数据库
//            List<UserPackInfo> rides = jdbcTemplate.query("SELECT * FROM `user_pack_info` where user_id =?", new RowMapper<UserPackInfo>(){
//                @Override
//                public UserPackInfo mapRow(ResultSet rs, int i) throws SQLException {
//                    UserPackInfo userPackInfo = new UserPackInfo();
//                    //  userPackInfo.setUser_id(rs.getLong("user_id"));
//                    // userPackInfo.setGoods_id(rs.getInt("goods_id"));
//                    userPackInfo.setGoods_name(rs.getString("goods_name"));
//                    // userPackInfo.setGoods_use(rs.getInt("goods_use"));
//                    userPackInfo.setGoods_num(rs.getInt("goods_num"));
//                    return userPackInfo;
//                }
//            },userId);
//            if (rides.size()>0){
//                StringBuffer stringBuffer = new StringBuffer();
//                for (UserPackInfo tmp:rides) {
//                    stringBuffer.append(tmp.getGoods_name()+": "+tmp.getGoods_num()+"\n");
//                }
//                return stringBuffer.toString();// 当存在多个plugin时，继续执行下一个plugin
//            }
//            return "背包为空";// 当存在多个plugin时，继续执行下一个plugin
//        }
//        //背包数据使用
//
//        return "efs";
//    }
}
