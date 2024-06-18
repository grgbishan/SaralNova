package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewBookingsPage {
    public static WebDriver driver;

    public static By newBookingRoomType = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/div[1]/div[1]/select");
    public static By newBookingDate = By.xpath("//*[@id=\"datetimes\"]");
    public static By newBookingGuestCount = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/div[1]/div[3]/input");
    public static By newBookingSearch = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/div[1]/div[5]/button");
    public static By dateErrorMsg = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/div[1]/div[2]/span");
    public static By availableRoomsText = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/div[2]/div[2]/h5");
    public static By monthYear = By.xpath("/html/body/div[5]/div[2]/div[1]/table/thead/tr[1]/th[2]");
    public static By guestCountInput = By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/input[1]");
    public static By nextBtn = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[3]/button");
    public static By room1 = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/div[2]/div[2]/div/label[1]/div");
    public static By selectedRoom1 = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/p[1]");
    public static By room2 = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/div[2]/div[2]/div/label[2]/div");
    public static By selectedRoom2 = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/p[3]");
    public static By crossBtn = By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[4]/a[1]/i[1]\n");
    public static By selectOptionsText = By.xpath("//p[contains(text(),'Select Options')]");
    public static By checkBox = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]");
    public static By costBox = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/div[1]/div[2]/div/h5");

    public static final String hotelNewBookings = "Room Info";
    public static final String currentMonthYear = "Jun 2024";
    public static final String newBookingDates = "2024/06/15 - 2024/06/25";
    public static final String guestCount = "5";




    public NewBookingsPage(WebDriver newDriver) {
        driver = newDriver;
    }

    public static void verifyThatNewBookingsIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[7]/a/p")).click();
        WebElement newBookings = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[1]/div/div/div[1]/h6"));
        String expectedNewBooking = newBookings.getText();
        Assertions.assertEquals(expectedNewBooking, hotelNewBookings);
    }

    public static void selectRoomType(String roomTypes){
        WebElement roomTypeDropDown = driver.findElement(newBookingRoomType);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Select roomType = new Select(roomTypeDropDown);
        roomType.selectByVisibleText(roomTypes);
    }

    public static void selectDate(String dates) throws InterruptedException {
        WebElement dateInput = driver.findElement(newBookingDate);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value= '"+ dates +"'", dateInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", dateInput);
        Thread.sleep(2000);
    }

    public static void selectTwoRooms(By roomNumber, By selectedRoom, String expRoomNo ){
        driver.findElement(roomNumber).click();
        String roomNo = driver.findElement(selectedRoom).getText();
        Assertions.assertEquals(expRoomNo, roomNo);
    }

    public static boolean isContext = false;
    public static void IsContent(By element, String message){
        driver.findElement(element).click();
        isContext = true;
        Assertions.assertTrue(isContext, message);

    }

    public static void findNextButton(By element){
        WebElement nextButton = driver.findElement(element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
    }

    public static void roomInfo() throws InterruptedException {
        selectDate(newBookingDates);
        selectDate(newBookingDates);
        driver.findElement(guestCountInput).sendKeys(guestCount);
        driver.findElement(newBookingSearch).click();
        Thread.sleep(3000);
        driver.findElement(room1).click();
        Thread.sleep(2000);
        findNextButton(nextBtn);
        Thread.sleep(2000);
        driver.findElement(nextBtn).click();
    }

}
