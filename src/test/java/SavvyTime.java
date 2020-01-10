import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.bind.Element;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SavvyTime {
    WebDriver webDriver;
    WebElement element;

    @BeforeClass
    public void openSite() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://savvytime.com/");

    }

    @Test
    //@Test(priority = 1)
    public void converterTime() throws InterruptedException {
        element = webDriver.findElement(By.xpath("//*[@id=\"main-menu\"]/ul/li[2]/a"));
        element.click();
        webDriver.findElement(By.xpath("//*[@id=\"time-search\"]")).sendKeys("hyderabad");
        WebDriverWait wait = new WebDriverWait(webDriver,60,100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"converter-quick-search-result\"]/a[1]")));
        WebElement element = webDriver.findElement(By.xpath("//*[@id=\"converter-quick-search-result\"]/a[1]"));
       // System.out.println(element);
        element.click();
        webDriver.findElement(By.xpath("//*[@id=\"time-search\"]")).sendKeys("alaska");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"converter-quick-search-result\"]/a[1]")));
        WebElement element2 = webDriver.findElement(By.xpath("//*[@id=\"converter-quick-search-result\"]/a[1]"));
        element2.click();

        WebElement element3 = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/header/div/h1"));
        Assert.assertTrue(element3.getText().contains("Hyderabad, India to AKDT"));

        System.out.println("Checked Converter");
    }

    @Test(dependsOnMethods = {"converterTime"})
    //@Test(priority = 3)
    public void swapTimes() throws InterruptedException {
        //converterTime();
        webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebElement swapButton = webDriver.findElement(By.xpath("//*[@id=\"converter-controls\"]/div/div[2]/div/div[2]/a"));
        swapButton.click();

        WebElement text = webDriver.findElement(By.xpath("//*[@id=\"converter-view\"]/div[1]/div[2]/div/span"));
        Assert.assertTrue(text.getText().contains("AKST/AKDT"));
        System.out.println(text.getText());

        WebElement text2 = webDriver.findElement(By.xpath("//*[@id=\"converter-view\"]/div[2]/div[2]/div/a"));
        Assert.assertTrue(text2.getText().contains("Hyderabad, India"));
        System.out.println(text2.getText());

        System.out.println("Checked Swap");
    }

    @Test(dependsOnMethods = {"swapTimes"})
    public void deleteItem()
    {
        //WebElement div = webDriver.findElement(By.xpath("//*[@id=\"converter-view\"]/div[2]"));
    }


    @AfterClass
    public void closeAll()
    {
        //webDriver.quit();
    }

}

