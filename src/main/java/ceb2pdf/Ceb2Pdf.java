package ceb2pdf;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;
import org.xvolks.jnative.Convention;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;


/**
 * ceb文件格式转换成pdf文件格式
 *
 * @author minjian
 *
 */
public class Ceb2Pdf  {
    private static String c2pUrl = "";

    private static void c2pUrlInit() {
        ClassLoader classLoader = Ceb2Pdf.class.getClassLoader();
        c2pUrl = classLoader.getResource("c2p_dll.dll").getPath();
        File file = new File(c2pUrl);
        // if(System.getProperty("os.name").startsWith("win") ||
        // System.getProperty("os.name").startsWith("Win")){
        c2pUrl = file.getAbsolutePath();
        // }
    }


    /**
     * ceb文件格式转换成pdf文件格式
     *

     *            文件名
     * @return 列内容的字符串
     * @throws Throwable
     */
    public static String ceb2Pdf(String path) throws Throwable {
        String fileName = "";
        String solutionPath = "e:\\";
        // 如果不是windows系统，不做pdf转换,直接返回。ceb转pdf只支持Windows系统
        if (c2pUrl.trim().length() == 0) {
            c2pUrlInit();
        }

        String dataPath = solutionPath;
        String cebName = path;
        File cebFile = new File(cebName);
        if (!cebFile.exists()) {
            return fileName;
        }
        cebName = cebFile.getAbsolutePath();
        String pdfName = path.substring(0, path.length() - 3) + "pdf";
        fileName = dataPath + pdfName;
        File pdfFile = new File(fileName);
        if (pdfFile.exists()) {
            return pdfName;
        }
        JNative f_xxx = new JNative(c2pUrl, "OpenDoc", Convention.STDCALL);
        f_xxx.setRetVal(Type.INT);
        f_xxx.setParameter(0, Type.STRING, cebName);
        f_xxx.invoke();
        return pdfName;
    }

    @Test
    public void test1() throws Throwable{
        Ceb2Pdf ceb2pdf=new Ceb2Pdf();
        ceb2pdf.ceb2Pdf("e:\\abc.ceb");
    }
}