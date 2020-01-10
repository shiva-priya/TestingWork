import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FinalTest {
    DateFormat dateFormat = new SimpleDateFormat("kk:mm");
    WebDriver webDriver;

    @BeforeClass
    public void openSite()
    {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://savvytime.com/");
    }
    @Test(description = "Checks Calendar functions")
    public void checkCalendar() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/converter']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Hyderabad");
        webDriver.findElement(By.xpath("//a[@data-id='india-hyderabad']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Austria");
        webDriver.findElement(By.xpath("//a[@data-id='austria-graz']")).click();
        webDriver.findElement(By.xpath("//div[@class='btn-group']//a[@class='permanent-link btn']")).click();

        WebElement element = webDriver.findElement(By.xpath("//*[@id=\"date-picker\"]/span"));
        element.click();
        WebElement monthDiv = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"));
        monthDiv.click();
        WebElement selectMonth = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr/td/span[12]"));
        selectMonth.click();
        WebElement date = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/table/tbody/tr[4]/td[6]"));
        date.click();

        WebElement text1 = webDriver.findElement(By.xpath("//*[@id=\"converter-view\"]/div[1]/div[4]/div/div[2]/div/div[2]/div"));
        Assert.assertTrue(text1.getText().contains("Fri, Dec 25"));
        System.out.println(text1.getText());

        WebElement text2 = webDriver.findElement(By.xpath("//*[@id=\"converter-view\"]/div[2]/div[4]/div/div[2]/div/div[2]/div"));
        Assert.assertTrue(text2.getText().contains("Fri, Dec 25"));
        System.out.println(text2.getText());
    }
    @Test(description = "Enters Two cities and checks the working of Converter")
    public void converter() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/converter']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Hyderabad");
        webDriver.findElement(By.xpath("//a[@data-id='india-hyderabad']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Austria");
        webDriver.findElement(By.xpath("//a[@data-id='austria-graz']")).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='table-time row']"));
        String[] place1 = {"Hyderabad","Graz"};
        Assert.assertTrue(elements.get(0).getText().contains(place1[0]));
        Assert.assertTrue(elements.get(1).getText().contains(place1[1]));
    }
    @Test(description = "Checks Swap Button")
    public void converterSwap() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/converter']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Hyderabad");
        webDriver.findElement(By.xpath("//a[@data-id='india-hyderabad']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Austria");
        webDriver.findElement(By.xpath("//a[@data-id='austria-graz']")).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='table-time row']"));
        String[] place1 = {"Hyderabad","Graz"};
        Assert.assertTrue(elements.get(0).getText().contains(place1[0]));
      //  Assert.assertTrue(elements.get(1).getText().contains(place1[1]));
        webDriver.findElement(By.xpath("//div[@class='btn-group']//a[@class='swap-tz btn']")).click();
        elements = webDriver.findElements(By.xpath("//div[@class='table-time row']"));
        Assert.assertTrue(elements.get(0).getText().contains(place1[1]));
        Assert.assertTrue(elements.get(1).getText().contains(place1[0]));

    }
    @Test(description = "Checks Link to Coversion Button")
    public void converterLink() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/converter']")).click();

        webDriver.findElement(By.id("time-search")).sendKeys("Hyderabad");
        webDriver.findElement(By.xpath("//a[@data-id='india-hyderabad']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Austria");
        webDriver.findElement(By.xpath("//a[@data-id='austria-graz']")).click();
        webDriver.findElement(By.xpath("//div[@class='btn-group']//a[@class='permanent-link btn']")).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='col-xs-12 collapse in']//div[@class='row share-url-wrap']"));
        String[] place1 ={"Include Time","Include Date"};
        Assert.assertTrue(elements.get(0).getText().contains(place1[0]));
    }
    @Test(description = "Checks Local Time Search Result")
    public void localTime() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/local']")).click();
        webDriver.findElement(By.id("place-search")).sendKeys("Hyderabad");
        webDriver.findElement(By.xpath("//a[@href='/local/india-hyderabad']")).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='content-block frame text-center']"));
        String place1 = "Hyderabad";
        Assert.assertTrue(elements.get(0).getText().contains(place1));
        System.out.println(elements.get(0).getText());
    }
    @Test(description = "Checks to Timer Tab")
    public void timer() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/timers']")).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='big-services-box col-xs-12 col-sm-6 ']"));
        String place1 = "Timer";
        Assert.assertTrue(elements.get(0).getText().contains(place1));
    }

    @AfterClass
    public void closeAll()
    {
        webDriver.quit();
    }

}