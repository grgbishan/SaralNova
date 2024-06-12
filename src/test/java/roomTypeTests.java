import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.RoomTypePage;
import pages.LoginPage;
import java.time.Duration;

public class roomTypeTests {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static RoomTypePage addRoomTp;
    public By duplicateRoomType = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div[1]");
    public By roomTypePopUp = By.xpath("/html/body/div[2]/div/div[2]");
    public static final String actualSearch = "Deluxe";
    public static final String expectedSearch = "Deluxe";


    @BeforeAll
    public static void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://uat-app.saralnova.com/login");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        loginPage.loginToApplication(LoginPage.correctEmail, LoginPage.correctPassword);
        addRoomTp = new RoomTypePage(driver);
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

    @Test
    public void verifyThatRoomTypeIsAdded(){
        addRoomTp.verifyThatRoomTypeIsOpen();
        addRoomTp.addRoomTypes();
        String expRoomType = driver.findElement(roomTypePopUp).getText();
        Assertions.assertEquals(expRoomType, "Room Type Created Successfully" );
    }

    @Test
    public void verifyDuplicateRoomTypeIsNotAdded(){
        addRoomTp.verifyThatRoomTypeIsOpen();
        addRoomTp.duplicateRoomTypes();
        String expectedRoomType = driver.findElement(duplicateRoomType).getText();
        Assertions.assertEquals(expectedRoomType, "The title has already been taken.");
    }

    @Test
    public void verifyThatRoomTypeIsUpdated(){
        addRoomTp.verifyThatRoomTypeIsOpen();
        addRoomTp.roomTypesUpdated();
        String expRoomType = driver.findElement(roomTypePopUp).getText();
        Assertions.assertEquals(expRoomType, "Room Type Updated Successfully" );
    }

    @Test
    public void verifyThatRoomTypeIsDeleted(){
        addRoomTp.verifyThatRoomTypeIsOpen();
        addRoomTp.roomTypesDeleted();
        String expRoomType = driver.findElement(roomTypePopUp).getText();
        Assertions.assertEquals(expRoomType, "Room Type Deleted Successfully" );
    }

    @Test
    public void verifyThatRoomTypeIsSearchable(){
        addRoomTp.verifyThatRoomTypeIsOpen();
        addRoomTp.searchRoomTypes();
        Assertions.assertEquals(expectedSearch, actualSearch );
    }

}
