package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RoomTypePage {
    public WebDriver driver;

    public RoomTypePage(WebDriver newDriver) {
        driver = newDriver;
    }
    public static final String hotelRoomTypes = "Room Types";
    public By roomType = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/h3");
    public By addRoomTypeBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/a");
    public By typeRoomType = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div/input");
    public By submitBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[2]/button");
    public static final String correctRoomType = "Deluxe";
    public static final String duplicateRoomType = "Premium";
    public static final String updatedRoomType = "Deluxe Suite";
    public By updateBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[2]/div/div[2]/div[1]/div/div[2]/table/tbody/tr/td[3]/div/a");
    public By deleteBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[2]/div/div[2]/div[1]/div/div[2]/table/tbody/tr[2]/td[3]/div/form/button/i");
    public By yesDeleteBtn = By.xpath("/html/body/div[5]/div/div[6]/button[1]");
    public By searchRoomType = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[2]/div/div[1]/div[3]/div/label/input");
    public static final String searchText = "Premium";


    public void verifyThatRoomTypeIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[3]/a")).click();
        WebElement roomTypes = driver.findElement(roomType);
        String expectedRoomTypes = roomTypes.getText();
        Assertions.assertEquals(expectedRoomTypes, hotelRoomTypes);
    }
    public void addRoomTypes(){
        driver.findElement(addRoomTypeBtn).click();
        driver.findElement(typeRoomType).sendKeys(correctRoomType);
        driver.findElement(submitBtn).click();
    }
    public void duplicateRoomTypes(){
        driver.findElement(addRoomTypeBtn).click();
        driver.findElement(typeRoomType).sendKeys(duplicateRoomType);
        driver.findElement(submitBtn).click();
    }

    public void roomTypesUpdated(){
        driver.findElement(updateBtn).click();
        driver.findElement(typeRoomType).clear();
        driver.findElement(typeRoomType).sendKeys(updatedRoomType);
        driver.findElement(submitBtn).click();
    }

    public void roomTypesDeleted(){
        driver.findElement(deleteBtn).click();
        driver.findElement(yesDeleteBtn).click();
    }

    public void searchRoomTypes(){
        driver.findElement(searchRoomType).sendKeys(searchText);
    }
}
