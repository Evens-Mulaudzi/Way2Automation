package Pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddUsersPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor initializes the WebDriver and WebDriverWait.
    public AddUsersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators for web elements on the page
    private By userListTable = By.cssSelector("table.table.table-striped");
    private By addUserButton = By.cssSelector("button.btn.btn-link.pull-right");
    private By firstNameInput = By.cssSelector("input[name='FirstName']");
    private By lastNameInput = By.cssSelector("input[name='LastName']");
    private By userNameInput = By.cssSelector("input[name='UserName']");
    private By passwordInput = By.cssSelector("input[name='Password']");
    private By customerFieldCompanyAAA = By.cssSelector("input[name='optionsRadios'][value='15']");
    private By customerFieldCompanyBBB = By.cssSelector("input[name='optionsRadios'][value='16']");
    private By roleDropdown = By.cssSelector("select[name='RoleId']");
    private By roleOptionAdmin = By.cssSelector("option[value='2']");
    private By roleOptionCustomer = By.cssSelector("option[value='1']");
    private By emailInput = By.name("Email");
    private By mobilePhoneInput = By.name("Mobilephone");
    private By saveButton = By.cssSelector("button.btn.btn-success");

    // Navigates to the user management page
    public void navigateToPage() {
        driver.get("http://www.way2automation.com/angularjs-protractor/webtables/");
    }

   // Waits until the user list table is visible on the page.
    public void waitForUserListTable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userListTable));
    }

    public void clickAddUserButton() throws InterruptedException {
        driver.findElement(addUserButton).click();
        Thread.sleep(300);
    }

    public void enterUserDetails(String firstName, String lastName, String username, String password, String email, String phone, boolean isCompanyAAA, boolean isAdmin) throws InterruptedException {
        driver.findElement(firstNameInput).sendKeys(firstName);
        Thread.sleep(300);

        driver.findElement(lastNameInput).sendKeys(lastName);
        Thread.sleep(300);

        driver.findElement(userNameInput).sendKeys(username);
        Thread.sleep(300);

        driver.findElement(passwordInput).sendKeys(password);
        Thread.sleep(300);

        if (isCompanyAAA) {
            driver.findElement(customerFieldCompanyAAA).click();
        } else {
            driver.findElement(customerFieldCompanyBBB).click();
        }

        driver.findElement(roleDropdown).click();
        Thread.sleep(300);
        if (isAdmin) {
            driver.findElement(roleOptionAdmin).click();
        } else {
            driver.findElement(roleOptionCustomer).click();
        }
        Thread.sleep(300);

        driver.findElement(emailInput).sendKeys(email);
        Thread.sleep(300);

        driver.findElement(mobilePhoneInput).sendKeys(phone);
        Thread.sleep(300);

        driver.findElement(saveButton).click();
        Thread.sleep(3000);
    }

    // Verifies if a user is added by checking the presence of the username in the table.
    // @return True if the user is added, otherwise false.

    public boolean isUserAdded(String username) {
        return driver.findElement(By.xpath("//td[contains(text(),'" + username + "')]")).isDisplayed();
    }
}
