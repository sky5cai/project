package cn.mangareader;

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
        String url = "https://www.mangareader.net/one-piece/1";
        String imgPreHtml = "";

        List<String> imgUrlList = new ArrayList<String>();
        System.out.println();
        Document doc= Jsoup.connect(url).get();
        Elements newsHeadlines = doc.select("#imgholder>a>img");
        String aImgUrl =null;
        for (Element headline : newsHeadlines) {
            System.out.println(headline.toString());
            aImgUrl = headline.attr("src");
            System.out.println(aImgUrl);

//            String lastSpilt = aImgUrl.substring(aImgUrl.lastIndexOf("/")+1,aImgUrl.length()-1);
//            String encodeLastSpilt = URLEncoder.encode(lastSpilt,"utf-8");
//            aImgUrl = aImgUrl.replace(lastSpilt,encodeLastSpilt);

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
        Document doc =Jsoup.connect(url).timeout(50000).get();

        //拿取下一页的地址
        String nextPageUrl = "";
        //read 多少页数
        /*Elements elements = doc.select("#pageMenu>option");
        for (int i = 0; i <elements.size() ; i++) {
            String text = elements.get(i).text();
            text = String.valueOf(Integer.valueOf(text)-1);
            StringBuffer stbr= new StringBuffer();
            stbr.append(imgUrl);
            stbr.setLength(stbr.length()-(text.length()+4));
            stbr.append(text).append(".jpg");
            imgUrlList.add(stbr.toString());
        }
        //截取下一章的连接
//        url = url.substring(0,url.lastIndexOf("/")+1);
//        url = url.substring(0,url.lastIndexOf("/"));
        url += "/";
        url+=elements.size();
        //重新读取下一章
        Document doc2 = Jsoup.connect(url).timeout(50000).get();
        Elements elements2 = doc2.select(".next>a");
        for (Element element:elements2) {
            nextPageUrl = elements2.get(0).attr("href");
            nextPageUrl = "https://www.mangareader.net"+nextPageUrl;
            System.out.println(nextPageUrl);
            break;
        }
        //解析网址第一张图
        String aImgUrl = null;
        Document doc3 = Jsoup.connect(nextPageUrl).get();
        Elements elements3 = doc3.select("#imgholder>a>img");
        for (Element element:elements3) {
            aImgUrl = element.attr("src");
        }*/
        Elements elements = doc.select(".next>a");
        for (Element element:elements) {
            nextPageUrl = elements.get(0).attr("href");
            nextPageUrl = "https://www.mangareader.net"+nextPageUrl;
            break;
        }
        //解析地址放入imgUrlList
        Document doc2 = Jsoup.connect(nextPageUrl).timeout(50000).get();
        //寻找网页
        Elements newsHeadlines = doc2.select("#imgholder>a>img");
        for (Element headline : newsHeadlines) {
            System.out.println(headline.toString());
            String aImgUrl = headline.attr("src");
            System.out.println(aImgUrl);

//            String lastSpilt = aImgUrl.substring(aImgUrl.lastIndexOf("/")+1,aImgUrl.length()-1);
//            String encodeLastSpilt = URLEncoder.encode(lastSpilt,"utf-8");
//            aImgUrl = aImgUrl.replace(lastSpilt,encodeLastSpilt);

            imgUrlList.add(aImgUrl);
        }

        if(!nextPageUrl.contains("/54")){
            listImage(nextPageUrl,imgUrlList);
        }
    }



    @Test
    public void getPicTest() throws IOException {
        String url="https://i9.mangareader.net/one-piece/1/one-piece-1668160.jpg";
//        String lastSpilt = url.substring(url.lastIndexOf("/")+1,url.length()-1);
//        System.out.println(lastSpilt);
//        String encodeLastSpilt = URLEncoder.encode(lastSpilt,"utf-8");
//        url = url.replace(lastSpilt,encodeLastSpilt);
        String fileName="99999";
        ImageUtils.getPic(url,fileName);
    }


}
