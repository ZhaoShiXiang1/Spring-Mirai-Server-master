package net.lz1998.pbbot.utis;

public class ZXXUtil {

    public static String change(String type){

        if (type == null || "".equals(type)){
            return  "双鱼座";
        }
        if (type.contains("白羊")){
            type="白羊座";
        }else if (type.contains("金牛")){
            type="金牛座";
        }else if (type.contains("双子")){
            type="双子座";
        }else if (type.contains("巨蟹")){
            type="巨蟹座";
        }else if (type.contains("狮子")){
            type="狮子座";
        }else if (type.contains("处女")){
            type="处女座";
        }else if (type.contains("天秤")){
            type="天秤座";
        }else if (type.contains("天蝎")){
            type="天蝎座";
        }else if (type.contains("射手")){
            type="射手座";
        }else if (type.contains("魔蝎")){
            type="魔蝎座";
        }else if (type.contains("水瓶")){
            type="水瓶座";
        }else if (type.contains("双鱼")){
            type="双鱼座";
        }else {
            type="双鱼座";
        }
        return type;
    }
//    public static String change(String type){
//
//        if (type == null || "".equals(type)){
//            return  "pisces";
//        }
//        if (type.contains("白羊")){
//            type="aries";
//        }else if (type.contains("金牛")){
//            type="taurus";
//        }else if (type.contains("双子")){
//            type="gemini";
//        }else if (type.contains("巨蟹")){
//            type="cancer";
//        }else if (type.contains("狮子")){
//            type="leo";
//        }else if (type.contains("处女")){
//            type="virgo";
//        }else if (type.contains("天秤")){
//            type="libra";
//        }else if (type.contains("天蝎")){
//            type="scorpio";
//        }else if (type.contains("射手")){
//            type="sagittarius";
//        }else if (type.contains("魔蝎")){
//            type="capricorn";
//        }else if (type.contains("水瓶")){
//            type="aquarius";
//        }else if (type.contains("双鱼")){
//            type="pisces";
//        }else {
//            type="pisces";
//        }
//
//
//        return type;
//    }
}
