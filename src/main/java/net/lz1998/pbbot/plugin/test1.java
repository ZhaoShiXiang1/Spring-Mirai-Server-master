package net.lz1998.pbbot.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test1 {
    public static void main(String[] args) {

        String str3 = null;
        String  text2="写一首古诗";
        StringBuffer buffer = new StringBuffer();
        // TODO Auto-generated method stub
        Process proc;
//      String str2="python C:\\Users\\PC\\IdeaProjects\\GPT\\gpt1.0\\test1.py "+text2;

        String str2="python C:\\Users\\Administrator\\Desktop\\test1.py "+text2;
        System.out.println("str2="+str2);
        try {
            proc = Runtime.getRuntime().exec(str2);// 执行py文件
            //用输入输出流来截取结果

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gb2312"));
            String line = null;
            buffer.append("你好:");
//            System.out.println(in.readLine());
            while ((line = in.readLine()) != null) {
                System.out.println("进入循环");
                System.out.println(line);
                buffer.append('\n');
                buffer.append(line.toString());

            }
//            System.out.println("结束");
            in.close();
            proc.waitFor();
//            System.out.println("-------------");
            str3= buffer.toString().replace("\n\n","\n");//替换为空古诗会出现异常
            System.out.println(str3);
//            return str3;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        return str3;
    }
}
