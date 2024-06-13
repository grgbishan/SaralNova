import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.ExtraCostsPage;
import pages.LoginPage;

import java.time.Duration;

import static pages.ExtraCostsPage.*;

public class extraCostsTest {
    public static WebDriver driver;
    public static ExtraCostsPage extCost;
    public static LoginPage login;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://uat-app.saralnova.com/login");
        extCost = new ExtraCostsPage(driver);
        login = new LoginPage(driver);
        login.loginToApplication(LoginPage.correctEmail, LoginPage.correctPassword);
        ExtraCostsPage.verifyThatExtraCostsIsOpen();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyThatTheAddNewBtnOfExtraCostsOpensAddNewPage() {
        inputBoxIsVisible(addNewBtn);
        String addPageText = driver.findElement(addNewPageText).getText();
        Assertions.assertEquals("Add Extra Costs", addPageText);
    }

    @Test
    public void verifyThatTheDateIsNotNull() {
        inputBoxIsVisible(addNewBtn);
        Assertions.assertNotNull(pickDate);
    }

    @Test
    public void verifyThatTheBookingIdIsNotNull() {
        inputBoxIsVisible(addNewBtn);
        Assertions.assertNotNull(bookingId);
    }

    @Test
    public void verifyThatTheRoomTitleIsNotNull() {
        inputBoxIsVisible(addNewBtn);
        Assertions.assertNotNull(roomTitleInput);
    }

    @Test
    public void verifyThatTheExtraCostsIsNotSearchableWithInValidData() throws InterruptedException {
        inputBoxIsVisible(addNewBtn);
        searchRoomForExtraCost("6", roomTitleKeys);
        String bookingDetails = driver.findElement(error_msg).getText();
        Assertions.assertEquals("Room not found", bookingDetails);
    }

    @Test
    public void verifyThatTheExtraCostsIsSearchableWithValidData() throws InterruptedException {
        inputBoxIsVisible(addNewBtn);
        searchRoomForExtraCost(bookingIdKeys, roomTitleKeys);
        String bookingDetails = driver.findElement(bookingDetailsText).getText();
        Assertions.assertEquals("Booking Details", bookingDetails);
    }

    @Test
    public void verifyThatTheCalculateBtnCalculatesTheAmountOfExtraCosts() throws InterruptedException {
        inputBoxIsVisible(addNewBtn);
        searchRoomForExtraCost(bookingIdKeys, roomTitleKeys);
        calculateTheExpenses(particularData, amountData, quantityData);
        String expenseCalculatedData = driver.findElement(expenseSubTotal).getAttribute("value");
        Assertions.assertEquals(expenseCalculatedData, expenseSubTotalInput);
    }

    @Test
    public void verifyThatTheSubmitBtnSubmitsTheExpenses() throws InterruptedException {
        inputBoxIsVisible(addNewBtn);
        searchRoomForExtraCost(bookingIdKeys, roomTitleKeys);
        calculateTheExpenses(particularData, amountData, quantityData);
        WebElement expenseSubmit = driver.findElement(expenseSubmitBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", expenseSubmit);
        Thread.sleep(2000);
        expenseSubmit.click();
        Thread.sleep(2000);
        String extraCostWebElement = driver.findElement(extraCostsToast).getText();
        Assertions.assertEquals(extraCostWebElement, "Extra Cost added successfully");
    }

    @Test
    public void verifyThatTheExpenseIsEditable(){
        driver.findElement(editBtn).click();
        String editExtraCostPage = driver.findElement(editExtCost).getText();
        Assertions.assertEquals("Update Payment", editExtraCostPage);
    }

    @Test
    public void verifyThatWhenTheValueOfPaidAmountIsKeptTheUpdateButtonIsVisible() throws InterruptedException {
        driver.findElement(editBtn).click();
        driver.findElement(paidAmount).sendKeys(discountAmtInput);
        Thread.sleep(2000);
        String paidAmt = driver.findElement(updateBtn).getText();
        Assertions.assertEquals("Update", paidAmt);
    }

    @Test
    public void verifyThatTheUpdateButtonUpdatesTheStatusAfterThePaymentIsDone() throws InterruptedException {
        driver.findElement(editBtn).click();
        driver.findElement(paidAmount).sendKeys(discountAmtInput);
        Thread.sleep(2000);
        driver.findElement(updateBtn).click();
        String updatedStatus = driver.findElement(extraCostsToast).getText();
        Assertions.assertEquals("Updated successful!", updatedStatus);
    }

    @Test
    public void verifyThatTheDeleteIconProcessesTheDelete() throws InterruptedException {
        driver.findElement(deleteBtn).click();
        String confirmDelete = driver.findElement(confirmDeleteText).getText();
        Assertions.assertEquals("Are you sure?", confirmDelete);
        driver.findElement(confirmDeleteBtn).click();
        String deleteConfirmation = driver.findElement(extraCostsToast).getText();
        Assertions.assertEquals("Extra Cost deleted successfully", deleteConfirmation);

    }
}
