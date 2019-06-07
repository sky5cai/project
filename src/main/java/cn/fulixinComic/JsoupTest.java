package cn.fulixinComic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import util.ImageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        Document doc= Jsoup.connect("http://www.ccdm10.com/manhua/14086/109598.html?p=1").get();
        String html = doc.html();
        System.out.println(html);
    }


    @Test
    //得到漫画的浏览地址进行解析，这里正则表达式拿去出来，拼成图片链接
    public void test2() throws Exception{
        //http://www.ccdm9.com/manhua/5776/163748.html?p=4
        //http://www.ccdm1.com/manhua/6725/44852.html?p=7

        //福利漫画
        String url = "https://manhua.zsh8.com/pxtt/pxtt-001/25712.html";
        String imgPreHtml = "";

        List<String> imgUrlList = new ArrayList<String>();
        System.out.println();
        Document doc= Jsoup.connect(url).get();
        Elements newsHeadlines = doc.select(".gallery-item>dt>a");
        for (Element headline : newsHeadlines) {
            System.out.println(headline.toString());
            String aImgUrl = headline.attr("href");
            System.out.println(aImgUrl);
            imgUrlList.add(aImgUrl);
        }

        int count=1;
        for (String imgHtml:imgUrlList) {
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
