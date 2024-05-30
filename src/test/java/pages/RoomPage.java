package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RoomPage {
    public static WebDriver driver;
    public static By addNewBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/a/span");
    public static By addNewPage = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div/h3");
    public static By roomTypeBox = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[1]/select");
    public static By roomStatus = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[3]/select");
    public static By submitRoom = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[2]/button");
    public static By errorTitle = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[2]/span");
    public static By errorRate = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[4]/span");
    public static By roomRate = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[4]/input");
    public static By roomTitle = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[2]/input");
    public static By amenities1 =By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[5]/div/table/tbody/tr/td[1]/div/div/label/span");
    public static By amenities2 =By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[5]/div/table/tbody/tr/td[2]/div/div/label/span");
    public static By amenities3 =By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[5]/div/table/tbody/tr/td[3]/div/div/label/span");
    public static By roomToastMsg = By.xpath("/html/body/div[2]/div/div[2]");
    public static By viewRoomBtn = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[3]/td[5]/div/a[1]");
    public static By roomDetailsPage = By.xpath("//*[@id=\"nonPrintableContent\"]/div[1]/h4");
    public static By editRoomBtn = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[3]/td[5]/div/a[2]");
    public static By editRoomText = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div/h3");
    public static By deleteRoomBtn = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[3]/td[5]/div/form/button");
    public static By confirmDeleteText = By.xpath("/html/body/div[5]/div/div[2]");
    public static By confirmDelete = By.xpath("/html/body/div[5]/div/div[6]/button[1]");
    public static final String hotelRoom = "Rooms";
    public static final String visibleRoomType = "Deluxe Ac";
    public static final String visibleRoomStatus = "VIP";
    public static final String roomTitleNo = "405";
    public static final String roomRatePerNight = "15000";
    public static final String editVisibleRoomType = "Deluxe Suite";
    public static final String editVisibleRoomStatus = "Unavailable";
    public static final String editRoomTitleNo = "305";
    public static final String editRoomRatePerNight = "1500";


    public RoomPage(WebDriver newDriver) {
        driver = newDriver;
    }
    
    public static void verifyThatRoomIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[4]/a/p")).click();
        WebElement room = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/h3"));
        String expectedRoom = room.getText();
        Assertions.assertEquals(expectedRoom, hotelRoom);
    }

    public static void ifAllRoomTypeAreShown(){
        WebElement roomTypeDropDown = driver.findElement(roomTypeBox);
        Select roomType = new Select(roomTypeDropDown);
        List<WebElement> options = roomType.getOptions();
        List<String> actualOptions = new ArrayList<>();
        for(WebElement option:options){
            actualOptions.add(option.getText());
        }
        List<String> expectedOptions = List.of("Premium", "Deluxe Suite", "Deluxe Ac", "Deluxe Nonac");
        boolean allOptionsPresent = actualOptions.containsAll(expectedOptions);
        if (allOptionsPresent) {
            System.out.println("All expected options are present in the dropdown.");
        } else {
            System.out.println("Some expected options are missing in the dropdown.");
        }
    }

    public static void selectRoomType(){
        WebElement roomTypeDropDown = driver.findElement(roomTypeBox);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Select roomType = new Select(roomTypeDropDown);
        roomType.selectByVisibleText(visibleRoomType);
    }

    public static void ifAllStatusArePresent(){
        WebElement roomStatusDropDown = driver.findElement(roomStatus);
        Select roomStat = new Select(roomStatusDropDown);
        List<WebElement> options = roomStat.getOptions();
        List<String> actualOptions = new ArrayList<>();
        for(WebElement option:options){
            actualOptions.add(option.getText());
        }
        List<String> expectedOptions = List.of("Availabe", "Cleaning", "Maintenance", "Unavailable", "Out Of Order", "VIP", "Check-out");
        boolean allOptionsPresent = actualOptions.containsAll(expectedOptions);
        if (allOptionsPresent) {
            System.out.println("All expected options are present in the dropdown.");
        } else {
            System.out.println("Some expected options are missing in the dropdown.");
        }
    }

    public static void selectStatus(){
        WebElement roomTypeDropDown = driver.findElement(roomStatus);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Select roomStatus = new Select(roomTypeDropDown);
        roomStatus.selectByVisibleText(visibleRoomStatus);
    }

    public static void selectAmenities(){
        driver.findElement(amenities1).click();
        driver.findElement(amenities3).click();
    }

    public static void allCorrectData(){
        driver.findElement(RoomPage.addNewBtn).click();
        selectRoomType();
        driver.findElement(roomTitle).sendKeys(roomTitleNo);
        selectStatus();
        driver.findElement(roomRate).sendKeys(roomRatePerNight);
        selectAmenities();
        driver.findElement(RoomPage.submitRoom).click();
    }

    public static void editRoomType(){
        WebElement roomTypeDropDown = driver.findElement(roomTypeBox);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Select roomType = new Select(roomTypeDropDown);
        roomType.selectByVisibleText(editVisibleRoomType);
    }
    public static void editStatus(){
        WebElement roomTypeDropDown = driver.findElement(roomStatus);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Select roomStatus = new Select(roomTypeDropDown);
        roomStatus.selectByVisibleText(editVisibleRoomStatus);
    }

    public static void editAmenities(){
        driver.findElement(amenities2).click();
    }

    public static void titleFieldClear(){
        WebElement reqField = driver.findElement(roomTitle);
        reqField.clear();
    }
    public static void rateFieldClear(){
        WebElement reqField = driver.findElement(roomRate);
        reqField.clear();
    }
    public static void editData(){
        driver.findElement(RoomPage.editRoomBtn).click();
        editRoomType();
        titleFieldClear();
        driver.findElement(roomTitle).sendKeys(editRoomTitleNo);
        editStatus();
        rateFieldClear();
        driver.findElement(roomRate).sendKeys(editRoomRatePerNight);
        selectAmenities();
        editAmenities();
        driver.findElement(RoomPage.submitRoom).click();
    }

}
