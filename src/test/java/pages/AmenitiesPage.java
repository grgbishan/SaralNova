package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmenitiesPage {
    public static WebDriver driver;
    public static final String hotelAmenities = "Amenities";
    public By imageSelectorBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[1]/input");
    public static By addAmenityTitle = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[2]/input");
    public By updateImageSelectorBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[1]/section/div/div/div[1]/input");
    public static By addAmenityBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[1]/a/span");
    public static By addAmenityText = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div/h3");
    public static By submitAmenity = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/form/div[2]/button");
    public static By duplicateAmenities = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div[1]");
    public static By viewBtnSelector = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[2]/div/div[2]/div[1]/div/div[2]/table/tbody/tr[1]/td[4]/div/a[1]/i");
    public static By amenitiesDetails = By.xpath("/html/body/div[1]/div[1]/div/div/section/div/div/div/div[1]/div[1]/h4");
    public static By editAmenitiesBtn = By.xpath("/html/body/div[1]/div[1]/div[2]/div/section/div/div/div/div/div[2]/div/div[2]/div[1]/div/div[2]/table/tbody/tr[1]/td[4]/div/a[2]");
    public static By amenitiesContainer = By.xpath("//*[@id=\"form\"]/div[1]/section/div/div/div[5]");

    public static By deleteAmenities = By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[4]/div/form/button");
    public static By sureDeleteDialog = By.xpath("/html/body/div[5]/div/div[2]");
    public static By yesDeleteIt = By.xpath("/html/body/div[5]/div/div[6]/button[1]");
    public static By updateSubmitBtn = By.xpath("//*[@id=\"button_submit\"]");
    public static final String title1 = "Toothbrush";
    public static final String title2 = "Soap";
    public static final String title3 = "Bath Robe";
    public static By amenitiesPopUp = By.xpath("/html/body/div[2]/div/div[2]");


    public AmenitiesPage(WebDriver newDriver) {
        driver = newDriver;
    }



    public static void verifyThatAmenitiesIsOpen(){
        driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[6]/a/p")).click();
        WebElement amemities = driver.findElement(By.xpath("/html/body/div[1]/aside/div/nav/ul/li[1]/ul/li[6]/a/p"));
        String expectedAmenities = amemities.getText();
        Assertions.assertEquals(expectedAmenities, hotelAmenities);
    }


    public void selectImage(){
        WebElement image = driver.findElement(imageSelectorBtn);
        String filePath = "C:\\Users\\gurun\\OneDrive\\Pictures\\SaralNova\\toothbrush.png";
        image.sendKeys(filePath);
    }

    public void updateImageAndTitle(){
        WebElement image = driver.findElement(updateImageSelectorBtn);
        String filePath = "C:\\Users\\gurun\\OneDrive\\Pictures\\SaralNova\\bathrobe.jpg";
        image.sendKeys(filePath);

        WebElement reqField = driver.findElement(addAmenityTitle);
        reqField.clear();
    }



}

