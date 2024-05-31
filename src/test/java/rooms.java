import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import pages.AmenitiesPage;
import pages.LoginPage;
import pages.RoomPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static pages.RoomPage.roomStatus;


public class rooms {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static RoomPage roomsPage;
    public static AmenitiesPage amenitiesPage;

    @BeforeAll
    public static void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://uat-app.saralnova.com/user/rooms");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        loginPage.loginToApplication(LoginPage.correctEmail, LoginPage.correctPassword);
        roomsPage = new RoomPage(driver);
        amenitiesPage = new AmenitiesPage(driver);

    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

    @Test
    public void verifyThatAddNewBtnOpensAddNewPage(){
        RoomPage.verifyThatRoomIsOpen();
        driver.findElement(RoomPage.addNewBtn).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String addNewRoomPage = driver.findElement(RoomPage.addNewPage).getText();
        Assertions.assertEquals(addNewRoomPage, "Add Rooms");
    }

    @Test
    public void verifyThatAllTheRoomTypesAreShownInRoomType(){
        RoomPage.verifyThatRoomIsOpen();
        driver.findElement(RoomPage.addNewBtn).click();
        RoomPage.ifAllRoomTypeAreShown();
    }

    @Test
    public void verifyThatRoomTitleIsRequired(){
        RoomPage.verifyThatRoomIsOpen();
        driver.findElement(RoomPage.addNewBtn).click();
        RoomPage.selectRoomType();
        RoomPage.selectStatus();
        driver.findElement(RoomPage.roomRate).sendKeys(RoomPage.roomRatePerNight);
        driver.findElement(RoomPage.submitRoom).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String errorTitleMsg = driver.findElement(RoomPage.errorTitle).getText();
        Assertions.assertEquals(errorTitleMsg, "Title is required.");
    }

    @Test
    public void verifyThatRoomStatusAreShownInStatus(){
        RoomPage.verifyThatRoomIsOpen();
        driver.findElement(RoomPage.addNewBtn).click();
        RoomPage.ifAllStatusArePresent();
    }

    @Test
    public void verifyThatRatePerNightIsRequired(){
        RoomPage.verifyThatRoomIsOpen();
        driver.findElement(RoomPage.addNewBtn).click();
        driver.findElement(RoomPage.submitRoom).click();
        String errorRateMsg = driver.findElement(RoomPage.errorRate).getText();
        Assertions.assertEquals(errorRateMsg, "Rate is required.");
    }


    @Test
    public void verifyThatRoomIsAddedWithCorrectData(){
        RoomPage.verifyThatRoomIsOpen();
        RoomPage.allCorrectData();
        String successAddingRoom = driver.findElement(RoomPage.roomToastMsg).getText();
        Assertions.assertEquals(successAddingRoom, "Room Created Successfully");
    }

    @Test
    public void verifyThatTheAddedAmenitiesAreDisplayedWhileAddingARoom(){
        RoomPage.verifyThatRoomIsOpen();
        driver.findElement(RoomPage.addNewBtn).click();
        WebElement amenities = driver.findElement(RoomPage.compareAmenitiesTitle);
        String amenitiesText = amenities.getText();
        Assertions.assertEquals(amenitiesText, ifAllAmenitiesArePresent());
    }

    @Test
    public void verifyThatTheViewBtnOpensDetailsOfRoom(){
        RoomPage.verifyThatRoomIsOpen();
        driver.findElement(RoomPage.viewRoomBtn).click();
        String roomDetails = driver.findElement(RoomPage.roomDetailsPage).getText();
        Assertions.assertEquals(roomDetails, "Rooms Details");
    }

    @Test
    public void verifyThatEditButtonOpensEditRoom(){
        RoomPage.verifyThatRoomIsOpen();
        driver.findElement(RoomPage.editRoomBtn).click();
        RoomPage.selectRoomType();
        driver.findElement(RoomPage.roomTitle).sendKeys(RoomPage.roomTitleNo);
        RoomPage.selectStatus();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String editRooms = driver.findElement(RoomPage.editRoomText).getText();
        Assertions.assertEquals(editRooms,"Edit Rooms");
    }

    @Test
    public void verifyThatSubmitButtonInEditRoomUpdatesData(){
        RoomPage.verifyThatRoomIsOpen();
        RoomPage.editData();
        String editRooms = driver.findElement(RoomPage.roomToastMsg).getText();
        Assertions.assertEquals(editRooms,"Room Updated Successfully");
    }

    @Test
    public void verifyThatDeleteBtnOpenDialogBoxToConfirmDelete(){
        RoomPage.verifyThatRoomIsOpen();
        driver.findElement(RoomPage.deleteRoomBtn).click();
        String sureDelete = driver.findElement(RoomPage.confirmDeleteText).getText();
        Assertions.assertEquals(sureDelete, "Are you sure?");
    }

    @Test
    public void verifyThatYesDeleteBtnDeletesTheRoom(){
        RoomPage.verifyThatRoomIsOpen();
        driver.findElement(RoomPage.deleteRoomBtn).click();
        driver.findElement(RoomPage.confirmDelete).click();
        String sureDelete = driver.findElement(RoomPage.roomToastMsg).getText();
        Assertions.assertEquals(sureDelete, "Room Deleted Successfully");
    }

    public static short ifAllAmenitiesArePresent() {
        WebElement amenitiesList = driver.findElement(RoomPage.compareAmenitiesTitle);
        String options = amenitiesList.getText();
        String actualOptions = new ArrayList<>().toString();
        for (WebElement option : options) {
            option.getText().matches(actualOptions);
        }
        return 0;
    }
}
