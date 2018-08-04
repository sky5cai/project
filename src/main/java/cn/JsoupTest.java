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
        Document doc= Jsoup.connect("http://www.ccdm1.com/manhua/6725/44852.html?p=7").get();
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

        String preHtml="http://ccmhimg.ufo001.com:8011";
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
        String url="https://mhpic.samanlehua.com/comic/M%2F%E6%A2%A6%E6%AF%94%E4%BC%98%E6%96%AF%E5%A5%A5%E7%89%B9%E6%9B%BC%E5%A4%96%E4%BC%A0%2F%E7%AC%AC2%E9%83%A801%2F17.jpg-noresize.webp";
        String fileName="a";
        ImageUtils.getPic(url,fileName);
    }


}
