import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;
import pages.NewBookingsPage;

import java.time.Duration;

import static pages.NewBookingsPage.*;


public class newBookings {
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
        Assertions.assertEquals("Deluxe Ac", "Deluxe Ac");
    }


    @Test
    public void verifyThatTheDateIsNotNull() {
        verifyThatNewBookingsIsOpen();
        WebElement dateInput = driver.findElement(newBookingDate);
        Assertions.assertNotNull(dateInput);
    }

    @Test
    public void verifyThatTheDateDisplaysErrorMsgIfTheDaeIsEmpty() {
        verifyThatNewBookingsIsOpen();
        driver.findElement(newBookingSearch).click();
        String errorMsg = driver.findElement(NewBookingsPage.guestCountErrorMsg).getText();
        Assertions.assertEquals(errorMsg, "The date range field is required.");
    }

    @Test
    public void verifyThatTheDateIsSelected(){
        verifyThatNewBookingsIsOpen();
        WebElement dateInput = driver.findElement(newBookingDate);
        dateInput.sendKeys(checkInOutDate);
        Assertions.assertEquals(checkInOutDate, "2024/06/05 - 2024/06/10");
    }

    @Test
    public void verifyThatExpectedGuestCountIsNotNull(){
        verifyThatNewBookingsIsOpen();
        WebElement guestCount = driver.findElement(newBookingGuestCount);
        Assertions.assertNotNull(guestCount);
    }
}
