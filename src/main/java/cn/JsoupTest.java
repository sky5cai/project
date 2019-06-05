package cn;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import util.ImageUtils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:zhancai
 * @Description:
 * @Date:Created   2018/8/4
 * @Modified by
 */
public class JsoupTest {

    @Test
    //jsoup拿去连接测试
    public void test1() throws IOException {
        Document doc= Jsoup.connect("http://www.ccdm1.com/manhua/9496/68239.html").get();
        String html = doc.html();
        System.out.println(html);
    }


    @Test
    //得到漫画的浏览地址进行解析，这里正则表达式拿去出来，拼成图片链接
    public void test2() throws Exception{
        //http://www.ccdm9.com/manhua/5776/163748.html?p=4
        //http://www.ccdm1.com/manhua/6725/44852.html?p=7
        Document doc= Jsoup.connect("http://www.ccdm9.com/manhua/10271/75878.html?p=10").get();
        String html = doc.html();
//        System.out.println(html);
        Pattern pa=Pattern.compile("\".*jpg\"");
        Matcher m=pa.matcher(html);
        int i=0;
        m.find();
        String text=m.group();
        System.out.println(text);

        text=text.substring(1,text.length()-1);
        //解析后
        System.out.println(text);
        //将\去掉
        String[] strArray=text.replace("\\","").replace("\"","").split(",");
        //去掉后的数组
        System.out.println(strArray[1]);

        String preHtml="http://img.danmeiwo.com";
        int count=1;
        for (String temp:strArray
             ) {
            String imgHtml=preHtml+temp;
            System.out.println(imgHtml);
            ImageUtils.getPic(imgHtml,String.valueOf(count++));
        }

    }



    @Test
    public void getPicTest() throws IOException {
        String url="https://image.zsh8.com/uploads/2019/02/12-il0.jpg";
        String fileName="a";
        ImageUtils.getPic(url,fileName);
    }


}
