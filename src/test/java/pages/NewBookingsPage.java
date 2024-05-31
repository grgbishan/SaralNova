package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static pages.RoomPage.visibleRoomType;

public class NewBookingsPage {
    public static WebDriver driver;

    public static By newBookingRoomType = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/div[1]/div[1]/select");
    public static By newBookingDate = By.xpath("//*[@id=\"datetimes\"]");
    public static By newBookingGuestCount = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/div[1]/div[3]/input");
    public static By newBookingSearch = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/div[1]/div[5]/button");
    public static By guestCountErrorMsg = By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[2]/div[1]/div[2]/span");
    public static final String hotelNewBookings = "Room Info";
    public static final String checkInOutDate = "2024/06/05 - 2024/06/10";

    public NewBookingsPage(WebDriver newDriver) {
        driver = newDriver;
    }

    public static void verifyThatNewBookingsIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[7]/a/p")).click();
        WebElement newBookings = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[1]/div/div/div[1]/h6"));
        String expectedNewBooking = newBookings.getText();
        Assertions.assertEquals(expectedNewBooking, hotelNewBookings);
    }

    public static void selectRoomType(){
        WebElement roomTypeDropDown = driver.findElement(newBookingRoomType);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Select roomType = new Select(roomTypeDropDown);
        roomType.selectByVisibleText(visibleRoomType);

    }
}
