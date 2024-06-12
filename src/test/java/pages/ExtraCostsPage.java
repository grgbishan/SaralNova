package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ExtraCostsPage {
    public static WebDriver driver;
    public static final String hotelExtraCosts = "Extra Costs";
    public static By addNewBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/a");
    public static By addNewPageText = By.xpath("/html/body/div[1]/div[1]/div/div/section/div/div/div/div/section/div/div/div/div[1]/h3");
    public static By bookingIDText = By.xpath("//input[@id='bookingId']");
    public static By roomTitleText = By.xpath("//input[@id='roomTitle']");
    public static By searchBtn = By.xpath("//button[@id='searchButton']");
    public static By pickDate = By.xpath("//*[@id='date']");
    public static By bookingDetailsText = By.xpath("//*[@id=\"expensesSection\"]/div[1]");
    public static By bookingId = By.xpath("//input[@id='bookingId']");
    public static By roomTitleInput = By.xpath("//*[@id=\"roomTitle\"]");
    public static By editExtCosts = By.xpath("//*[@id=\"editExtCosts\"]");
    public static By deletePopUp = By.xpath("//*[@id=\"deletePopUp\"]");
    public static By deleteExtCosts = By.xpath("//*[@id=\"deleteExtCosts\"]");
    public static final String bookingIdKeys = "7";
    public static final String roomTitleKeys = "405";
    public static final String dateKeys = "2025-01-14";

    public ExtraCostsPage(WebDriver newDriver) {
        driver = newDriver;
    }

    public static void verifyThatExtraCostsIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[9]/a/p")).click();
        WebElement extraCosts = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/h3"));
        String expectedExtraCosts = extraCosts.getText();
        Assertions.assertEquals(expectedExtraCosts, hotelExtraCosts);
    }

    public static void selectDate() throws InterruptedException {
        WebElement date = driver.findElement(pickDate);
        date.sendKeys(dateKeys);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '2024-06-11';", dateKeys);
        Thread.sleep(2000);
    }

    public static void addExtraCost() throws InterruptedException {
        driver.findElement(addNewBtn).click();
        selectDate();
        driver.findElement(bookingIDText).sendKeys(bookingIdKeys);
        driver.findElement(roomTitleText).sendKeys(roomTitleKeys);
        driver.findElement(searchBtn).click();
        Thread.sleep(2000);
    }

    public static void addBtnIsVisible(By element ){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

}
