import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.AmenitiesPage;
import pages.LoginPage;

import java.time.Duration;

import static pages.AmenitiesPage.*;

public class amenitiesTests {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static AmenitiesPage amenitiesPage;


    @BeforeAll
    public static void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://uat-app.saralnova.com/login");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        loginPage.loginToApplication(LoginPage.correctEmail, LoginPage.correctPassword);
        amenitiesPage = new AmenitiesPage(driver);
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }

    @Test
    public void verifyThatAddAmenitiesIsOpened(){
        amenitiesPage.verifyThatAmenitiesIsOpen();
        driver.findElement(addAmenityBtn).click();
        String addAmenity = driver.findElement(addAmenityText).getText();
        Assertions.assertEquals(addAmenity, "Add Amenities");
    }

    @Test
    public void verifyThatImageIsNullable(){
        verifyThatAddAmenitiesIsOpened();
        driver.findElement(addAmenityTitle).sendKeys(title2);
        driver.findElement(submitAmenity).click();
        loginPage.popUpAlert();
        String amenities = driver.findElement(amenitiesPopUp).getText();
        Assertions.assertEquals(amenities, "Amenities Added Successfully.");
    }

    @Test
    public void verifyThatAmenitiesIsAdded(){
        verifyThatAddAmenitiesIsOpened();
        amenitiesPage.selectImage();
        driver.findElement(addAmenityTitle).sendKeys(title1);
        driver.findElement(submitAmenity).click();
        loginPage.popUpAlert();
        String amenities = driver.findElement(amenitiesPopUp).getText();
        Assertions.assertEquals(amenities, "Amenities Added Successfully.");
    }

    @Test
    public void verifyDuplicateAmenitiesDataIsNotAccepted(){
        verifyThatAddAmenitiesIsOpened();
        amenitiesPage.selectImage();
        driver.findElement(addAmenityTitle).sendKeys(title1);
        driver.findElement(submitAmenity).click();
        String amenities = driver.findElement(duplicateAmenities).getText();
        Assertions.assertEquals(amenities, "There is already another amenities of same name.");
    }

    @Test
    public void verifyThatEyeBtnViewsTheDetailsOfAmenities(){
        amenitiesPage.verifyThatAmenitiesIsOpen();
        driver.findElement(viewBtnSelector).click();
        loginPage.popUpAlert();
        String amenityTitle = driver.findElement(amenitiesPopUp).getText();
        Assertions.assertEquals(amenityTitle, "Amenities Details");
    }

    @Test
    public void verifyThatAmenitiesIsUpdated(){
        amenitiesPage.verifyThatAmenitiesIsOpen();
        driver.findElement(editAmenitiesBtn).click();
        amenitiesPage.updateImageAndTitle();
        driver.findElement(addAmenityTitle).sendKeys(title3);
        driver.findElement(updateSubmitBtn).click();
        loginPage.popUpAlert();
        String amenities = driver.findElement(amenitiesPopUp).getText();
        Assertions.assertEquals(amenities, "Amenities Updated Successfully.");
    }

    @Test
    public void verifyThatAmenitiesIsDeleteDialogOpens(){
        amenitiesPage.verifyThatAmenitiesIsOpen();
        driver.findElement(deleteAmenities).click();
        loginPage.popUpAlert();
        String sureDelete = driver.findElement(sureDeleteDialog).getText();
        Assertions.assertEquals(sureDelete, "Are you sure?");


    }

    @Test
    public void verifyThatAmenitiesIsDeletedSuccessfully(){
        amenitiesPage.verifyThatAmenitiesIsOpen();
        driver.findElement(deleteAmenities).click();
        driver.findElement(yesDeleteIt).click();
        loginPage.popUpAlert();
        String amenitiesDeleted = driver.findElement(amenitiesPopUp).getText();
        Assertions.assertEquals(amenitiesDeleted, "Amenities Deleted Successfully.");

    }

}
