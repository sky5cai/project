package util;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author:zhancai
 * @Description:
 * @Date:Created in  2018/8/4
 * @Modified by
 */
public class ImageUtils {
    //得到图片地址，和图片的名称，这里默认保存到e://img文件里

    /**
     * 根据网络连接现在图片，及命名
     * @param url1 网络连接
     * @param fileName 文件名
     * @throws IOException
     */
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

    /**
     * 个人爱好：根据图片的数字在目录上复制图片次数
     * @param srcFilePath 原始图片目录位置路径
     * @param count 复制次数
     * @throws IOException
     */
    public static void getPicByFilePath(String srcFilePath,int count) throws IOException {
        String parentFilePath=srcFilePath.substring(0,srcFilePath.lastIndexOf("\\"));
        File parentFile=new File(parentFilePath);
        File[] files=parentFile.listFiles();


        // 设置数据缓冲
        byte[] bs = new byte[1024 * 2];
        // 读取到的数据长度
        //过滤jpg格式
        int jpgCount=0;
        for (File fileTemp:files) {
            if(fileTemp.getAbsolutePath().endsWith("jpg")){
                jpgCount++;
            }
        }
        int len;

        // 输出的文件流保存图片至本地
        for(int i=0;i<count;i++){
            InputStream input = new FileInputStream(new File(srcFilePath));
            OutputStream os = new FileOutputStream(parentFilePath+"\\"+(++jpgCount)+".jpg");
            while ((len = input.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            input.close();
        }


    }


}
