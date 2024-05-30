package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public WebDriver driver;
    public By email = By.xpath("//*[@id=\"email\"]");
    public By password = By.xpath("//*[@id=\"password\"]");
    public By loginBtn = By.xpath("/html/body/div[1]/div[2]/form/div[4]/button");
    public By popUp = By.xpath("/html/body/div[2]/div/div[2]");
    public static final String correctEmail = "forestway@sns.com";
    public static final String correctPassword = "12345678";
    public LoginPage(WebDriver newDriver){
        driver = newDriver;
    }

    public void loginToApplication(String e_mail, String paswrd){
        driver.findElement(email).sendKeys(e_mail);
        driver.findElement(password).sendKeys(paswrd);
        driver.findElement(loginBtn).click();
    }
    public void popUpAlert(){
        driver.findElement(popUp).getText();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

}
