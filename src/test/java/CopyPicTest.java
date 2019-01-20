import org.junit.Test;
import util.ImageUtils;

import java.io.IOException;

/**
 * @Author:zhanCai
 * @Description:
 * @Date:Created in  16:47 2018/8/4
 * @Modified by
 */
public class CopyPicTest {

    @Test
    public void copyPic() throws IOException {
        ImageUtils.getPicByFilePath("F:\\我的坚果云\\1.jpg",50);
    }
}
