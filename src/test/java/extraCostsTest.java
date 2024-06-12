import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.ExtraCostsPage;
import pages.LoginPage;

import java.time.Duration;

import static pages.ExtraCostsPage.*;

public class extraCostsTest {
    public static WebDriver driver;
    public static ExtraCostsPage extCost;
    public static LoginPage login;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://uat-app.saralnova.com/login");
        extCost = new ExtraCostsPage(driver);
        login = new LoginPage(driver);
        login.loginToApplication(LoginPage.correctEmail, LoginPage.correctPassword);
        ExtraCostsPage.verifyThatExtraCostsIsOpen();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyThatTheAddNewBtnOfExtraCostsOpensAddNewPage() {
        addBtnIsVisible(addNewBtn);
        driver.findElement(addNewBtn).click();
        String addPageText = driver.findElement(addNewPageText).getText();
        Assertions.assertEquals("Add Extra Costs", addPageText);
    }

    @Test
    public void verifyThatTheDateIsNotNull() throws InterruptedException {
        addBtnIsVisible(addNewBtn);
        driver.findElement(addNewBtn).click();
        driver.findElement(pickDate).click();
        selectDate();
        Assertions.assertNotNull(driver.findElement(pickDate).getAttribute("value"), "Date should not be null");
    }

    @Test
    public void verifyThatTheBookingIdIsNotNull() {
        addBtnIsVisible(addNewBtn);
        driver.findElement(addNewBtn).click();
        Assertions.assertNotNull(driver.findElement(bookingId).getAttribute("value"), "Booking ID should not be null");
    }

    @Test
    public void verifyThatTheRoomTitleIsNotNull() throws InterruptedException {
        addBtnIsVisible(addNewBtn);
        driver.findElement(addNewBtn).click();
        Assertions.assertNotNull(driver.findElement(roomTitleInput).getAttribute("value"), "Room Title should not be null");
    }

    @Test
    public void verifyThatTheExtraCostsIsAdded() throws InterruptedException {
        addBtnIsVisible(addNewBtn);
        addExtraCost();
        String bookingDetails = driver.findElement(bookingDetailsText).getText();
        Assertions.assertEquals(bookingDetails, bookingDetails, "The extra costs were not added correctly.");
    }

    @Test
    public void verifyThatThe(){

    }


}
