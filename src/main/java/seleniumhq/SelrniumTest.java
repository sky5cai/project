package seleniumhq;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Author:zhanCai
 * @Description:
 * @Date:Created in  23:41 2019/5/3
 * @Modified by
 */
public class SelrniumTest {
    @Test
    public void test1() throws Exception{
        String chromedriver = "D:\\softwave\\drives\\MicrosoftWebDriver.exe";
        System.setProperty("webdriver.edge.driver", chromedriver);
        WebDriver driver = new EdgeDriver();

        driver.get("https://huaban.com/pins/2413930555/?jv8anu4q");

        Actions action = new Actions(driver);

        action.contextClick(driver.findElement(By.id("baidu_image_holder"))).sendKeys(Keys.ARROW_DOWN).sendKeys("V").perform();
        action.sendKeys(Keys.ARROW_DOWN);
        action.sendKeys(Keys.ARROW_DOWN);
        action.sendKeys(Keys.ARROW_DOWN);
        action.sendKeys(Keys.ARROW_DOWN);
        action.sendKeys(Keys.ARROW_DOWN);

        String title = driver.getTitle();
        action.sendKeys("V");
        action.perform();
        System.out.printf(title);

        driver.close();

    }
}
