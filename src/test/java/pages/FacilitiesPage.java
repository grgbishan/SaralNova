package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FacilitiesPage {
    public static WebDriver driver;

    public static By addFacilitiesBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/a/span");
    public static By addFacilityOpen = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div/h3");
    public static By addFacilityTitle = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[1]/input");
    public static By addSubmitFacility = By.xpath("//*[@id=\"button_submit\"]");
    public static By addPrice = By.xpath("//*[@id=\"form\"]/div[1]/section/div/div/div[2]/input");
    public static By addFacilityStatus = By.xpath("//*[@id=\"status\"]");
    public static By toastFacility = By.xpath("/html/body/div[2]/div/div[2]");
    public static By viewBtn = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[5]/div/a[1]");
    public static By viewDetails = By.xpath("//*[@id=\"nonPrintableContent\"]/div[1]/h4");
    public static By editFacilityBtn = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[5]/div/a[2]");
    public static By updateBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[2]/button");
    public static By deleteFacility = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[5]/div/form/button");
    public static By yesDeleteFacility = By.xpath("/html/body/div[5]/div/div[6]/button[1]");
    public static By editFacilityTtle = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[1]/input");
    public static By editPriceFacility = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[2]/input");
    public static By editFacilityStatus = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[3]/select");
    public static By goBackBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[2]/a");
    public static final String hotelFacility = "Facility";
    public static final String facilityTitle = "BreakFast";
    public static final String facilityPrice = "450";
    public static final String statusAvailable = "Available";
    public static final String statusUnAvailable = "Unavailable";
    public static final String editFacilityTitle = "PickUp/Drop";
    public static final String editFacilityPrice = "500";



    public FacilitiesPage(WebDriver newDriver) {
        driver = newDriver;
    }


    public static void verifyThatFacilitiesIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[5]/a/p")).click();
        WebElement facility = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/h3"));
        String expectedFacility = facility.getText();
        Assertions.assertEquals(expectedFacility, hotelFacility);
    }

    public static void facilityAvailability(){
        WebElement dropDownStatus = driver.findElement(addFacilityStatus);
        Select status = new Select(dropDownStatus);
        status.selectByVisibleText(statusAvailable);
    }
    public static void facilityUnAvailability(){
        WebElement dropDownStatus = driver.findElement(editFacilityStatus);
        Select status = new Select(dropDownStatus);
        status.selectByVisibleText(statusUnAvailable);
    }
    public static void addFacility(){
        driver.findElement(addFacilitiesBtn).click();
        driver.findElement(addFacilityTitle).sendKeys(facilityTitle);
        priceClear();
        driver.findElement(addPrice).sendKeys(facilityPrice);
        driver.findElement(addFacilityStatus).click();
        facilityAvailability();
        driver.findElement(addSubmitFacility).click();
    }
    public static void editFacilities(){
        driver.findElement(editFacilityBtn).click();
        titleClear();
        driver.findElement(editFacilityTtle).sendKeys(editFacilityTitle);
        priceClear();
        driver.findElement(editPriceFacility).sendKeys(editFacilityPrice);
        driver.findElement(editFacilityStatus).click();
        facilityUnAvailability();
        driver.findElement(updateBtn).click();
    }
    public static void priceClear(){
        driver.findElement(addPrice).clear();
    }
    public static void titleClear(){
        driver.findElement(addFacilityTitle).clear();
    }
}
