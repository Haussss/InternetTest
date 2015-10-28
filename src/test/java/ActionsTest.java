import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionsTest {
    private WebDriver driver;
    private final static String BASE_URL = "http://the-internet.herokuapp.com/";


    @BeforeMethod
    public void setup() {
      //  driver = new ChromeDriver();
        driver = new FirefoxDriver();
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
    public void sendKeysTest() throws InterruptedException {
        driver.findElement(By.linkText("Key Presses")).click();
        Actions actions = new Actions(driver);

        for (int i = 80; i < 90; i++) {
            String enterValue = (String.valueOf((char) i));
            actions.sendKeys(enterValue).perform();
            //        actions.sendKeys(Keys.CONTROL + "a"); //hotkey
            actions.contextClick();
            Thread.sleep(1000);
            WebElement displayed = driver.findElement(By.id("result"));
            Assert.assertEquals(displayed.getText(), "You entered: " + enterValue);
            Thread.sleep(500);

        }

    }

    @Test
    public void contextMenu() throws InterruptedException {

        driver.findElement(By.linkText("Context Menu")).click();
        Actions actions = new Actions(driver);
        WebElement contextButton = driver.findElement(By.id("hot-spot"));

        actions
                .contextClick(contextButton)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER).perform();
        Thread.sleep(1500);
    }
@Test
    public void hoverTest(){
    driver.findElement(By.linkText("Hovers")).click();
    Actions action = new Actions(driver);
    List<WebElement> hoverLists = driver.findElements(By.cssSelector(".figure"));
int counter = 1;
    for (int i = 0; i < hoverLists.size(); i++) {
        action.moveToElement(hoverLists.get(i)).click().perform();
        Assert.assertTrue(hoverLists.get(i).findElement(By.tagName("h5")).isDisplayed());
        Assert.assertEquals(hoverLists.get(i).findElement(By.tagName("h5")).getText(),"name: user" + counter);
        counter++;
    }

    }

@Test
    public void dragAndDropTest() throws InterruptedException {
    driver.findElement(By.linkText("Drag and Drop")).click();
    WebElement source = driver.findElement(By.id("column-a"));
    WebElement target = driver.findElement(By.id("column-b"));
    Actions action = new Actions(driver);
   // action.dragAndDrop(source,target).perform();
    action
            .clickAndHold(source)
          //  .moveToElement(target)
            .moveByOffset(200,300)
            .release()
            .perform();
    action.moveToElement(source).perform();
    Thread.sleep(2000);
    action.click(source);
    Thread.sleep(2000);
    action.clickAndHold(source).perform();
    Thread.sleep(2000);
    action.moveToElement(target).perform();
    Thread.sleep(2000);
    action.release().perform();
    Assert.assertEquals(source.findElement(By.tagName("header")).getText(),"B");
    Assert.assertEquals(target.findElement(By.tagName("header")).getText(),"A");

}



}




