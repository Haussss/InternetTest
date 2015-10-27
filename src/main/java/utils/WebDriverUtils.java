package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class WebDriverUtils {
    public static boolean isElementExist(WebDriver driver, By by){
        List<WebElement> elementList = driver.findElements(by);
        return !elementList.isEmpty();
    }

}
