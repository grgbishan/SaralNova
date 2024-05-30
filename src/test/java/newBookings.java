import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;
import pages.NewBookingsPage;

import java.time.Duration;

public class newBookings {
    public static WebDriver driver;
    public static LoginPage loginpage;
    public static NewBookingsPage newBooking;

    @BeforeAll
    public static void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://uat-app.saralnova.com/login");
        driver.manage().window().maximize();
        loginpage = new LoginPage(driver);
        loginpage.loginToApplication(LoginPage.correctEmail, LoginPage.correctPassword);
        newBooking = new NewBookingsPage(driver);
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }


}
