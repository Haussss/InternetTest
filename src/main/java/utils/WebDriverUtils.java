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

    public static void check(WebElement checkbox) {
        setCheckBoxTo(checkbox,true);

    }

    public static void uncheck(WebElement checkbox) {
        setCheckBoxTo(checkbox, false);
    }
    private static void setCheckBoxTo(WebElement checkbox, boolean value){
        if(checkbox.isSelected()!= value){}
        checkbox.click();

    }
}
