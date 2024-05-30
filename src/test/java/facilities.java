import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.FacilitiesPage;
import pages.LoginPage;

import java.time.Duration;

import static pages.FacilitiesPage.*;

public class facilities {
    public static WebDriver driver;
    public static FacilitiesPage facilities;
    public static LoginPage loginPage;

    @BeforeAll
    public static void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://uat-app.saralnova.com/login");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        loginPage.loginToApplication(LoginPage.correctEmail, LoginPage.correctPassword);
        facilities = new FacilitiesPage(driver);

    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void addFacilityPageIsOpen() {
        verifyThatFacilitiesIsOpen();
        driver.findElement(addFacilitiesBtn).click();
        String addFacility = driver.findElement(addFacilityOpen).getText();
        Assertions.assertEquals(addFacility, "Add Facility");
        driver.findElement(goBackBtn).click();
    }

    @Test
    public void verifyThatFaclityIsAddedWithData() {
        verifyThatFacilitiesIsOpen();
        addFacility();
        String facilityAdded = driver.findElement(toastFacility).getText();
        Assertions.assertEquals(facilityAdded, "Facility Created Successfully");
    }

    @Test
    public void verifyThatFacilityDetailsIsOpened() {
        verifyThatFacilitiesIsOpen();
        driver.findElement(viewBtn).click();
        String viewFacility = driver.findElement(viewDetails).getText();
        Assertions.assertEquals(viewFacility, "Facility Details");
    }

    @Test
    public void verifyThatFacilityIsUpdated() {
        verifyThatFacilitiesIsOpen();
        editFacilities();
        String updateFacility = driver.findElement(toastFacility).getText();
        Assertions.assertEquals(updateFacility, "Facility Updated Successfully");
    }

    @Test
    public void verifyThatFacilityIsDeletedSuccessfully(){
        verifyThatFacilitiesIsOpen();
        driver.findElement(deleteFacility).click();
        driver.findElement(yesDeleteFacility).click();
        String amenitiesDeleted = driver.findElement(toastFacility).getText();
        Assertions.assertEquals(amenitiesDeleted, "Facility Removed Successfully");

    }
}
