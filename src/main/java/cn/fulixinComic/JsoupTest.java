package cn.fulixinComic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import util.ImageUtils;

import java.io.IOException;
import java.net.URLEncoder;
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

            String lastSpilt = aImgUrl.substring(aImgUrl.lastIndexOf("/")+1,aImgUrl.length()-1);
            String encodeLastSpilt = URLEncoder.encode(lastSpilt,"utf-8");
            aImgUrl = aImgUrl.replace(lastSpilt,encodeLastSpilt);

            imgUrlList.add(aImgUrl);
        }

        //进行翻页
        listImage(url,imgUrlList);

        int count=1;
        for (String imgHtml:imgUrlList) {
            System.out.println(imgHtml);
            try{
                ImageUtils.getPic(imgHtml,String.valueOf(count++));
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }


    public void listImage(String url,List imgUrlList) throws IOException {
        Document doc =Jsoup.connect(url).get();
        //拿取下一页的地址
        String nextPageUrl = "";
        Elements elements = doc.select(".fusion-single-navigation-wrapper>a");
//        for (Element element:elements) {
            nextPageUrl = elements.get(elements.size()-1).attr("href");
//            break;
//        }
        //解析地址放入imgUrlList
        Document doc2 = Jsoup.connect(nextPageUrl).get();
        //寻找网页
        Elements newsHeadlines = doc2.select(".gallery-item>dt>a");
        for (Element headline : newsHeadlines) {
            System.out.println(headline.toString());
            String aImgUrl = headline.attr("href");
            System.out.println(aImgUrl);

            String lastSpilt = aImgUrl.substring(aImgUrl.lastIndexOf("/")+1,aImgUrl.length()-1);
            String encodeLastSpilt = URLEncoder.encode(lastSpilt,"utf-8");
            aImgUrl = aImgUrl.replace(lastSpilt,encodeLastSpilt);

            imgUrlList.add(aImgUrl);
        }
        //继续寻找下一页
        //如果第一页和最后一页连接相等，则不执行listImage
        if(!elements.get(0).attr("href").equals(elements.get(elements.size()-1).attr("href")) || elements.get(elements.size()-1).attr("href").contains("002") ){
            listImage(nextPageUrl,imgUrlList);
        }
    }



    @Test
    public void getPicTest() throws IOException {
        String url="https://image.zsh8.com/uploads/2017/11/07_已压缩-cot.jpg";
        String lastSpilt = url.substring(url.lastIndexOf("/")+1,url.length()-1);
        System.out.println(lastSpilt);
        String encodeLastSpilt = URLEncoder.encode(lastSpilt,"utf-8");
        url = url.replace(lastSpilt,encodeLastSpilt);
        String fileName="99999";
        ImageUtils.getPic(url,fileName);
    }


}
