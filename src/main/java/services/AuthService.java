package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WebDriverUtils;


public class AuthService {

    private static final By loginLocator = By.cssSelector("input[id='username']");
    private static final By pswdLocator = By.cssSelector("input[id='password']");
    private static final By errorLocator = By.id("flash");


    public static boolean isErrorMesageDisplayed(WebDriver driver, String login, String pswd) {
        WebElement loginField = driver.findElement(loginLocator);
        loginField.sendKeys(login);
        WebElement passField = driver.findElement(pswdLocator);
        passField.sendKeys(pswd);
        driver.findElement(By.cssSelector("i[class='fa fa-2x fa-sign-in']")).click();
        WebElement errorMessage = driver.findElement(errorLocator);
        return errorMessage.isDisplayed();
    }


    public static boolean isErrorMessageDisplayedAndCorrect(WebDriver driver, String login, String pswd,String errorMessageText) {
        WebElement loginField = driver.findElement(loginLocator);
        loginField.sendKeys(login);
        WebElement passField = driver.findElement(pswdLocator);
        passField.sendKeys(pswd);
        driver.findElement(By.cssSelector("i[class='fa fa-2x fa-sign-in']")).click();

        if(WebDriverUtils.isElementExist(driver,errorLocator)){
            WebElement errorMessage = driver.findElement(errorLocator);
            return errorMessage.getText().contains(errorMessageText);
        }

        return false;
    }


}
