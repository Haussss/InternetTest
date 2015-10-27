import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.AuthService;
import utils.WebDriverUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class InternetTest {
    private WebDriver driver;
    private final static String BASE_URL = "http://the-internet.herokuapp.com/";
    private final static String ERROR_MESSAGEPSWD = "Your password is invalid!";
    private final static String ERROR_MESSAGEUSRN = "Your username is invalid!";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void elementGetVisibleTest() {
        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.findElement(By.partialLinkText("Example 1")).click();
        WebElement startButton = driver.findElement(By.cssSelector("#start button"));
        WebElement finishBlock = driver.findElement(By.id("finish"));
        startButton.click();
        Assert.assertFalse(startButton.isDisplayed(), "Start button is not dissapear");
        WebDriverWait wait = new WebDriverWait(driver, 12);
        wait.until(ExpectedConditions.visibilityOf(finishBlock));
        Assert.assertTrue(finishBlock.isDisplayed(), "Finish block is visible");
        Assert.assertEquals(finishBlock.getText(), "Hello World!");
    }

    @Test
    public void elementAppearedTest() {
        driver.findElement(By.linkText("Dynamic Loading")).click();
        driver.findElement(By.partialLinkText("Example 2")).click();
        WebElement startButton = driver.findElement(By.cssSelector("#start button"));
        startButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 13);
        //  wait.until(ExpectedConditions.elementSelectionStateToBe(By.id("loading"), false));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        WebElement finishBlock = driver.findElement(By.id("finish"));
        Assert.assertEquals(finishBlock.getText(), "Hello World!");

    }

    @Test
    public void loginTest() {
        driver.findElement(By.linkText("Form Authentication")).click();
        WebElement login = driver.findElement(By.id("username"));
        login.sendKeys("tomsmith");
        WebElement pass = driver.findElement(By.id("password"));
        pass.sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector(".radius")).click();
        WebElement finishButton = driver.findElement(By.cssSelector(".icon-2x.icon-signout"));
        Assert.assertTrue(finishButton.isDisplayed(), "Finish button is visible");
        Assert.assertEquals(finishButton.getText(), "Logout");
        WebElement finishTitle = driver.findElement(By.id("flash"));
        Assert.assertTrue(finishTitle.isDisplayed(), "Finish title is visible");
        Assert.assertTrue(finishTitle.getText().contains("You logged into a secure area!"));

    }

    @Test
    public void logotTest() {
        driver.findElement(By.linkText("Form Authentication")).click();
        WebElement login = driver.findElement(By.id("username"));
        login.sendKeys("tomsmith");
        WebElement pass = driver.findElement(By.id("password"));
        pass.sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector(".radius")).click();
        driver.findElement(By.cssSelector(".button.secondary.radius")).click();
        WebElement logoutTitle = driver.findElement(By.id("flash"));
        Assert.assertTrue(logoutTitle.isDisplayed(), "Logout title is visible");
        Assert.assertTrue(logoutTitle.getText().contains("You logged out of the secure area!"));
        WebElement logoutText = driver.findElement(By.cssSelector(".example"));
        Assert.assertTrue(logoutText.getText().contains("Login Page"));
    }

    @Test
    public void validationTest() {
        driver.findElement(By.linkText("Form Authentication")).click();
        Assert.assertTrue(AuthService.isErrorMessageDisplayedAndCorrect(driver, "wronglogin", "SuperSecretPassword!",
                ERROR_MESSAGEUSRN), "Error message isn't displayed");
        Assert.assertTrue(AuthService.isErrorMessageDisplayedAndCorrect(driver, "tomsmith", "WrondPassword",
                ERROR_MESSAGEPSWD), "Error message isn't displayed");
        Assert.assertTrue(AuthService.isErrorMessageDisplayedAndCorrect(driver, "", "",
                ERROR_MESSAGEUSRN), "Error message isn't displayed");
        Assert.assertTrue(AuthService.isErrorMessageDisplayedAndCorrect(driver, "wronglogin", "wrongpassword",
                ERROR_MESSAGEUSRN), "Error message isn't displayed");

    }


 @Test
    public void checkBoxTest(){
        driver.findElement(By.linkText("Checkboxes")).click();
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        WebDriverUtils.check(checkboxes.get(0));
        WebDriverUtils.uncheck(checkboxes.get(1));
        Assert.assertTrue(checkboxes.get(0).isSelected());
        Assert.assertFalse(checkboxes.get(1).isSelected());
    }
}