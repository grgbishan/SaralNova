import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.NewBookingsPage;

import java.time.Duration;

import static pages.NewBookingsPage.*;
import static pages.RoomPage.visibleRoomType;


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
        verifyThatNewBookingsIsOpen();
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

    @Test
    public void verifyThatRoomTypeIsSelected(){
        NewBookingsPage.selectRoomType(visibleRoomType);
        Assertions.assertEquals(visibleRoomType, "Deluxe Ac");
    }


    @Test
    public void verifyThatTheDateIsNotNull() {
        WebElement dateInput = driver.findElement(newBookingDate);
        Assertions.assertNotNull(dateInput);
    }

    @Test
    public void verifyThatTheDateDisplaysErrorMsgIfTheDateIsEmpty() {
        driver.findElement(newBookingSearch).click();
        String errorMsg = driver.findElement(NewBookingsPage.dateErrorMsg).getText();
        Assertions.assertEquals(errorMsg, "The date range field is required.");
    }

    @Test
    public void verifyThatExpectedGuestCountIsNotNull(){
        WebElement guestCount = driver.findElement(newBookingGuestCount);
        Assertions.assertNotNull(guestCount);
    }

    @Test
    public void verifyThatTheCalenderDisplaysDateFromPresentTime(){
        driver.findElement(newBookingDate).click();
        String calenderMonthYear = driver.findElement(monthYear).getText();
        Assertions.assertEquals(currentMonthYear, calenderMonthYear);
    }

    @Test
    public void verifyThatTheSearchButtonDisplaysTheRoomWithoutRoomType() throws InterruptedException {
        selectDate(newBookingDates);
        driver.findElement(newBookingSearch).click();
        Thread.sleep(3000);
        String bookingDetails = driver.findElement(availableRoomsText).getText();
        Assertions.assertEquals("Available Rooms",bookingDetails);
    }

    @Test
    public void verifyThatMultipleRoomsCanBeSelected() throws InterruptedException {
        selectDate(newBookingDates);
        driver.findElement(guestCountInput).sendKeys(guestCount);
        driver.findElement(newBookingSearch).click();
        Thread.sleep(2000);
        selectTwoRooms(room1, selectedRoom1,"Room:123");
        selectTwoRooms(room2, selectedRoom2,"Room:201");
    }

    @Test
    public void verifyThatTheCrossBtnCancelsAndClearsTheDateForBookings() throws InterruptedException {
        selectDate(newBookingDates);
        driver.findElement(guestCountInput).sendKeys(guestCount);
        driver.findElement(newBookingSearch).click();
        Thread.sleep(2000);
        selectTwoRooms(room1, selectedRoom1, "Room:123");
        driver.findElement(crossBtn).click();
        Thread.sleep(2000);
        IsContent(crossBtn, "The date was not deleted.");
    }

    @Test
    public void verifyThatTheNextBtnIsEnabled() throws InterruptedException {
        selectDate(newBookingDates);
        selectDate(newBookingDates);
        driver.findElement(guestCountInput).sendKeys(guestCount);
        driver.findElement(newBookingSearch).click();
        driver.findElement(room1).click();
        Thread.sleep(2000);
        boolean  isEnabled = driver.findElement(roomInfoNextBtn).isEnabled();
        Assertions.assertTrue(isEnabled, "The next button is disabled");
    }

    @Test
    public void verifyThatWhenNextBtnIsClickedItRedirectsToTheOptionsPage() throws InterruptedException {
        selectDate(newBookingDates);
        selectDate(newBookingDates);
        driver.findElement(guestCountInput).sendKeys(guestCount);
        driver.findElement(newBookingSearch).click();
        Thread.sleep(3000);
        driver.findElement(room1).click();
        findNextButton(roomInfoNextBtn);
        Thread.sleep(2000);
        driver.findElement(roomInfoNextBtn).click();
        Thread.sleep(3000);
        String selectOptions = driver.findElement(selectOptionsText).getText();
        Assertions.assertEquals("Select Options", selectOptions);
    }

    @Test
    public void verifyThatTheCheckBoxAreClickable() throws InterruptedException {
        roomInfo();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(checkBox));
        checkbox.click();
        IsContent(checkBox, "Checkbox is not clickable");
    }

    @Test
    public void verifyThatWhenTheCheckBoxIsClickedTheCostIsAddedAccordingToTheDayCount() throws InterruptedException {
        roomInfo();
        Thread.sleep(2000);
        checkBoxAddOrSubtractsTheCost();
    }

    @Test
    public void verifyThatTheGuestInfoPageIsOpen() throws InterruptedException {
        guestInfo();
        WebElement number3GuestInfo = driver.findElement(guestInfoNumber3);
        String colour = number3GuestInfo.getCssValue("color");
        Assertions.assertEquals( "rgb(255, 255, 255)", colour);
    }

    @Test
    public void verifyThatTheCitizenshipNumberIsNeededForNepaliCitizen() throws InterruptedException {
        guestInfo();
        newBooking.dropDownInput(nationalityInput, nationalityText);
        Thread.sleep(2000);
        Assertions.assertNotNull(citizenshipInput);
    }

    @Test
    public void verifyThatTheCitizenshipNumberIsNotNeededForOtherNationality() throws InterruptedException {
        guestInfo();
        newBooking.dropDownInput(nationalityInput, otherNationalityText);
        Thread.sleep(2000);
        boolean isCitizenshipInputDisplayed = false;
        Assertions.assertFalse(isCitizenshipInputDisplayed, "The citizenship input box is not displayed");
    }

    @Test
    public void verifyThatTheNameInGuestInfoAreRequired() throws InterruptedException {
        guestInfo();
        driver.findElement(optionsNextBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(guestInfoNextBtn));
        driver.findElement(guestInfoNextBtn).click();
        Thread.sleep(2000);
        required(nameReqMsg, nameReqMsgText);
    }

    @Test
    public void verifyThatTheContactInGuestInfoAreRequired() throws InterruptedException {
        guestInfo();
        driver.findElement(optionsNextBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(guestInfoNextBtn));
        driver.findElement(guestInfoNextBtn).click();
        Thread.sleep(2000);
        required(contactReqMsg, contactReqMsgText);
    }


}
