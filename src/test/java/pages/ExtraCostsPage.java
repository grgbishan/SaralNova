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
    public static By extraCostsToast = By.xpath("//*[@id=\"toast-container\"]/div/div[2]");
    public static By addNewBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/a");
    public static By addNewPageText = By.xpath("/html/body/div[1]/div[1]/div/div/section/div/div/div/div/section/div/div/div/div[1]/h3");
    public static By bookingIDText = By.xpath("//input[@id='bookingId']");
    public static By roomTitleText = By.xpath("//input[@id='roomTitle']");
    public static By searchBtn = By.xpath("//button[@id='searchButton']");
    public static By pickDate = By.xpath("//*[@id='date']");
    public static By bookingDetailsText = By.xpath("//h5[contains(text(),'Booking Details')]");
    public static By bookingId = By.xpath("//input[@id='bookingId']");
    public static By roomTitleInput = By.xpath("//*[@id=\"roomTitle\"]");
    public static By error_msg = By.xpath("//*[@id=\"message\"]");
    public static By expenseParticular = By.xpath("//*[@id=\"item1\"]/div/div[1]/input");
    public static By expenseAmount = By.xpath("//*[@id=\"item1\"]/div/div[2]/input");
    public static By expenseQuantity = By.xpath("//*[@id=\"item1\"]/div/div[3]/input");
    public static By expenseCalculateBtn = By.xpath("//*[@id=\"calculateBtn\"]");
    public static By expenseSubTotal = By.xpath("//*[@id=\"subtotal\"]");
    public static By expenseSubmitBtn = By.xpath("//*[@id=\"button_submit\"]");
    public static By editExtCost = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div/div[2]/h4");
    public static By paidAmount = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div/div[2]/input[5]");
    public static By editBtn = By.xpath("//tbody/tr[4]/td[8]/div[1]/a[2]/i[1]");
    public static By updateBtn = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div/div[2]/div[1]/button");
    public static By deleteBtn = By.xpath("//tbody/tr[7]/td[8]/div[1]/form[1]/button[1]");
    public static By confirmDeleteText = By.xpath("//div[@id='swal2-html-container']");
    public static By confirmDeleteBtn = By.xpath("//button[contains(text(),'Yes, delete it!')]");
    public static final String bookingIdKeys = "7";
    public static final String roomTitleKeys = "405";
    public static final String dateKeys = "2025-01-14";
    public static final String particularData = "Laundry";
    public static final String amountData = "150";
    public static final String quantityData = "5";
    public static final String expenseSubTotalInput = "750";
    public static final String discountAmtInput = "750";


    public ExtraCostsPage(WebDriver newDriver) {
        driver = newDriver;
    }

    public static void verifyThatExtraCostsIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[9]/a/p")).click();
        WebElement extraCosts = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/h3"));
        String expectedExtraCosts = extraCosts.getText();
        Assertions.assertEquals(expectedExtraCosts, hotelExtraCosts);
    }

    public static void selectDate(String dateInput) throws InterruptedException {
        WebElement date = driver.findElement(pickDate);
        date.sendKeys(dateInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '2024-06-11'", dateKeys);
        Thread.sleep(2000);
    }

    public static void searchRoomForExtraCost(String bookingId, String roomTitle) throws InterruptedException {
        selectDate(dateKeys);
        driver.findElement(bookingIDText).sendKeys(bookingId);
        driver.findElement(roomTitleText).sendKeys(roomTitle);
        driver.findElement(searchBtn).click();
        Thread.sleep(2000);
    }

    public static void inputBoxIsVisible(By element ){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).click();
    }

    public static void calculateTheExpenses(String particular, String amount, String quantity){
        driver.findElement(expenseParticular).sendKeys(particular);
        driver.findElement(expenseAmount).sendKeys(amount);
        WebElement quantityInput = driver.findElement(expenseQuantity);
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
        driver.findElement(expenseCalculateBtn).click();
    }


}
