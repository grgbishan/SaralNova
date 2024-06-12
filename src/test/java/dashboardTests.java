import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;

import java.time.Duration;

public class dashboardTests {
    public static WebDriver driver;
    public static LoginPage dashboardPage;
    public static final String actualName = "Forestway";
    public static final String hotelCalender = "May";
    public static final String hotelRoomTypes = "Room Types";
    public static final String hotelRoom = "Rooms";
    public static final String hotelFacility = "Facility";
    public static final String hotelAmenities = "Amenities";
    public static final String hotelNewBookings = "Room Info";
    public static final String hotelBookings = "Bookings";
    public static final String hotelExtraCosts = "Extra Costs";
    public static final String activityLogs = "Activity Log";
    public static final String staffLog = "Staffs";
    public static final String restDashboard = "Dashboard";
    public static final String restDB = "Categories";
    private By restaraunt = By.xpath(" /html/body/div[1]/aside/div/nav/ul/li[4]/a/p");
    public static final String restCategory = "Add Category";
    public static final String restMenu = "Add Menu";
    public static final String restVariant = "Variants";

    @BeforeEach
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://uat-app.saralnova.com/login");
        driver.manage().window().maximize();
        dashboardPage = new LoginPage(driver);
        dashboardPage.loginToApplication(LoginPage.correctEmail, LoginPage.correctPassword );
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void verifyThatDashboardIsOpen() {
        WebElement nameElement = driver.findElement(By.xpath("/html/body/div[1]/nav/ul[2]/li/a"));
        String expectedName = nameElement.getText();
        Assertions.assertEquals(expectedName, actualName);
    }

    @Test
    public void verifyThatHotelDashboardIsOpen(){
        String hotelDashboard = driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[1]")).getText();
        Assertions.assertEquals(hotelDashboard, "Dashboard");
    }

    @Test
    public void verifyThatHotelCalenderIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[2]")).click();
        WebElement calender = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div/div[1]/div/div"));
        String expectedCalender = calender.getText();
        Assertions.assertEquals(expectedCalender, hotelCalender);
    }

    @Test
    public void verifyThatRoomTypeIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[3]/a")).click();
        WebElement roomTypes = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/h3"));
        String expectedRoomTypes = roomTypes.getText();
        Assertions.assertEquals(expectedRoomTypes, hotelRoomTypes);
    }

    @Test
    public void verifyThatRoomIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[4]/a/p")).click();
        WebElement room = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/h3"));
        String expectedRoom = room.getText();
        Assertions.assertEquals(expectedRoom, hotelRoom);
    }

    @Test
    public void verifyThatFacilitiesIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[5]/a/p")).click();
        WebElement facility = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/h3"));
        String expectedFacility = facility.getText();
        Assertions.assertEquals(expectedFacility, hotelFacility);
    }

    @Test
    public void verifyThatAmenitiesIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[6]/a/p")).click();
        WebElement amemities = driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[6]/a/p"));
        String expectedAmenities = amemities.getText();
        Assertions.assertEquals(expectedAmenities, hotelAmenities);
    }

    @Test
    public static void verifyThatNewBookingsIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[7]/a/p")).click();
        WebElement newBookings = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div/div/div/div[1]/div/div/div[1]/h6"));
        String expectedNewBooking = newBookings.getText();
        Assertions.assertEquals(expectedNewBooking, hotelNewBookings);
    }

    @Test
    public void verifyThatBookingsIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[8]/a/p")).click();
        WebElement Bookings = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/h3"));
        String expectedBooking = Bookings.getText();
        Assertions.assertEquals(expectedBooking, hotelBookings);
    }

    @Test
    public void verifyThatExtraCostsIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[9]/a/p")).click();
        WebElement extraCosts = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/h3"));
        String expectedExtraCosts = extraCosts.getText();
        Assertions.assertEquals(expectedExtraCosts, hotelExtraCosts);
    }

    @Test
    public void verifyThatActivityLogIsOpen(){
        driver.findElement(By.xpath(" /html/body/div[1]/aside/div/nav/ul/li[2]/a/p")).click();
        WebElement activityLog = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/section/div/div/div/div/div[1]/h5"));
        String expectedActivityLog = activityLog.getText();
        Assertions.assertEquals(expectedActivityLog, activityLogs);
    }

    @Test
    public void verifyThatStaffIsOpen(){
        driver.findElement(By.xpath(" /html/body/div[1]/aside/div/nav/ul/li[3]/a/p")).click();
        WebElement staff = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/h3"));
        String expectedStaff = staff.getText();
        Assertions.assertEquals(expectedStaff, staffLog);
    }

    @Test
    public void verifyThatRestarauntDropDownIsOpen(){
        driver.findElement(By.xpath(" /html/body/div[1]/aside/div/nav/ul/li[4]/a/p")).click();
        WebElement restarauntDashboard = driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[4]/ul/li[1]/a/p"));
        String expectedRestarauntDashboard = restarauntDashboard.getText();
        Assertions.assertEquals(expectedRestarauntDashboard, restDashboard);
    }

    @Test
    public void verifyThatRestarauntDashBoardIsOpen(){
        driver.findElement(restaraunt).click();
        driver.findElement(By.xpath(" /html/body/div[1]/aside/div/nav/ul/li[4]/ul/li[1]/a/p")).click();
        WebElement restarauntDb = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[1]/div/div[1]/div/div/div[1]/span"));
        String expectedDb = restarauntDb.getText();
        Assertions.assertEquals(expectedDb, restDB);
    }

    @Test
    public void verifyThatRestarauntCategoryIsOpen(){
        driver.findElement(restaraunt).click();
        driver.findElement(By.xpath(" /html/body/div[1]/aside/div/nav/ul/li[4]/ul/li[2]/a/p")).click();
        WebElement restarauntCategory = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/section/div/div/div/div/div/div/div/div[1]/div[2]/button"));
        String expectedCategory = restarauntCategory.getText();
        Assertions.assertEquals(expectedCategory, restCategory);
    }

    @Test
    public void verifyThatRestarauntMenuIsOpen(){
        driver.findElement(restaraunt).click();
        driver.findElement(By.xpath(" /html/body/div[1]/aside/div/nav/ul/li[4]/ul/li[3]/a/p")).click();
        WebElement restarauntMenu = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/section/div/div/div/div/div/div/div[1]/div[3]/button"));
        String expectedMenu = restarauntMenu.getText();
        Assertions.assertEquals(expectedMenu, restMenu);
    }

    @Test
    public void verifyThatRestarauntVariantIsOpen(){
        driver.findElement(restaraunt).click();
        driver.findElement(By.xpath(" /html/body/div[1]/aside/div/nav/ul/li[4]/ul/li[4]/a/p")).click();
        WebElement restarauntVariant = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/section/div/div/div/div/div/div/div[2]/div/div[1]/h5"));
        String expectedVariant = restarauntVariant.getText();
        Assertions.assertEquals(expectedVariant, restVariant);
    }

}
