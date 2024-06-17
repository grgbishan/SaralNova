import jdk.jfr.Event;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;
import pages.NewBookingsPage;
import pages.RoomPage;

import java.time.Duration;

import static pages.NewBookingsPage.*;


public class newBookingsTests {
    public static WebDriver driver;
    public static LoginPage loginpage;
    public static NewBookingsPage newBooking;

    @BeforeAll
    public static void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://uat-app.saralnova.com/login");
        driver.manage().window().maximize();
        loginpage = new LoginPage(driver);
        loginpage.loginToApplication(LoginPage.correctEmail, LoginPage.correctPassword);
        newBooking = new NewBookingsPage(driver);
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

    @Test
    public void verifyThatRoomTypeIsSelected(){
        verifyThatNewBookingsIsOpen();
        NewBookingsPage.selectRoomType();
        Assertions.assertEquals(RoomPage.visibleRoomType, "Deluxe Ac");
    }


    @Test
    public void verifyThatTheDateIsNotNull() {
        verifyThatNewBookingsIsOpen();
        WebElement dateInput = driver.findElement(newBookingDate);
        Assertions.assertNotNull(dateInput);
    }

    @Test
    public void verifyThatTheDateDisplaysErrorMsgIfTheDateIsEmpty() {
        verifyThatNewBookingsIsOpen();
        driver.findElement(newBookingSearch).click();
        String errorMsg = driver.findElement(NewBookingsPage.dateErrorMsg).getText();
        Assertions.assertEquals(errorMsg, "The date range field is required.");
    }

    @Test
    public void verifyThatExpectedGuestCountIsNotNull(){
        verifyThatNewBookingsIsOpen();
        WebElement guestCount = driver.findElement(newBookingGuestCount);
        Assertions.assertNotNull(guestCount);
    }

    @Test
    public void verifyThatTheCalenderDisplaysDateFromPresentTime(){
        verifyThatNewBookingsIsOpen();
        driver.findElement(newBookingDate).click();
        String calenderMonthYear = driver.findElement(monthYear).getText();
        Assertions.assertEquals(currentMonthYear, calenderMonthYear);
    }

    @Test
    public void verifyThatTheSearchButtonDisplaysTheRoomWithoutRoomType() throws InterruptedException {
        verifyThatNewBookingsIsOpen();
        WebElement dateInput = driver.findElement(newBookingDate);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='2025/01/08 - 2025/01/14'", dateInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", dateInput);
        Thread.sleep(2000);
        driver.findElement(newBookingSearch).click();
        Thread.sleep(3000);
        String bookingDetails = driver.findElement(availableRoomsText).getText();
        Assertions.assertEquals("Available Rooms",bookingDetails);
    }





}
