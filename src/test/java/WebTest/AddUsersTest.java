package WebTest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import ExtentReport.Reporting;
import Pageobjects.AddUsersPage;

public class AddUsersTest {

    private WebDriver driver;
    private AddUsersPage addUsersPage;
    ExtentReports reports;
    public Reporting repo = new Reporting();

    //Sets up the WebDriver and initializes the page object.
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        addUsersPage = new AddUsersPage(driver);
    }

    //Initializes the ExtentReports before any tests are run.
    @BeforeTest
    public void init() {
        reports = repo.initializeExtentReports("reports/report.html");
    }

    //Navigates to the user management page before each test method.
    @BeforeMethod
    public void navigateToPage() {
        addUsersPage.navigateToPage();
    }

    //Test case for adding User 1.
    @Test(priority = 1)
    public void AddUser1() throws InterruptedException, IOException {
        ExtentTest test = reports.createTest("Add User 1");
        test.assignAuthor("Mulaudzi Thompho Evens");

        addUsersPage.waitForUserListTable();
        System.out.println("User List Table Is Displayed");

        addUsersPage.clickAddUserButton();
        addUsersPage.enterUserDetails("FNAme1", "LName1", "User1", "pass1", "admin@mail.com", "082555", true, true);

        boolean isUserAdded = addUsersPage.isUserAdded("User1");
        if (isUserAdded) {
            System.out.println("User1 was added successfully.");
            takeScreenshot(driver, test);
            test.pass("User1 was added successfully.");
        } else {
            System.out.println("User1 was not added.");
            takeScreenshot(driver, test);
            test.fail("User1 was not added.");
        }
    }

     //Test case for adding User 2.
    @Test(priority = 2)
    public void AddUser2() throws InterruptedException, IOException {
        ExtentTest test = reports.createTest("Add User 2");
        test.assignAuthor("Mulaudzi Thompho Evens");

        addUsersPage.clickAddUserButton();
        addUsersPage.enterUserDetails("FName2", "LName2", "User2", "pass2", "customer@mail.com", "083444", false, false);

        boolean isUserAdded = addUsersPage.isUserAdded("User2");
        if (isUserAdded) {
            System.out.println("User2 was added successfully.");
            takeScreenshot(driver, test);
            test.pass("User2 was added successfully.");
        } else {
            System.out.println("User2 was not added.");
            takeScreenshot(driver, test);
            test.fail("User2 was not added.");
        }
    }


    //Takes a screenshot and attaches it to the ExtentTest report.
    public static void takeScreenshot(WebDriver driver, ExtentTest test) throws IOException {
        String fileName = "screenshot_" + System.currentTimeMillis() + ".png";
        String directory = System.getProperty("user.dir") + "/screenshots/";
        new File(directory).mkdirs();
        String path = directory + fileName;
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(path));
        test.addScreenCaptureFromPath(path);
    }

    @AfterTest
    public void aftertest() throws InterruptedException {
        Thread.sleep(300);
        reports.flush();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
