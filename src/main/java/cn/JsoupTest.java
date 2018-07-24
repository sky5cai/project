package cn;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Document doc= Jsoup.connect("http://www.ccdm1.com/manhua/18596/143507.html?p=1").get();
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
            getPic(imgHtml,String.valueOf(count++));
        }

    }


    //得到图片地址，和图片的名称，这里默认保存到e://img文件里
    public static void getPic(String url1,String fileName) throws IOException {
        // 构造URL
        URL url = new URL(url1);
        // 打开URL连接
        URLConnection con = url.openConnection();
        // 得到URL的输入流
        InputStream input = con.getInputStream();
        // 设置数据缓冲
        byte[] bs = new byte[1024 * 2];
        // 读取到的数据长度
        int len;
        // 输出的文件流保存图片至本地

        OutputStream os = new FileOutputStream("E:\\img\\"+fileName+".jpg");
        while ((len = input.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        os.close();
        input.close();
    }
}
