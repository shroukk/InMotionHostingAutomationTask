package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.P01_Homepage;
import pages.P02_DomainsPage;
import pages.P03_CartPage;
import pages.P04_HostPage;
import utils.ExtentManager;
import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;


public class T01_BaseTest {

    protected WebDriver driver;
    P01_Homepage p01_homepage;
    P02_DomainsPage p02_domainsPage;
    P03_CartPage p03_cartPage;
    P04_HostPage p04_hostPage;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    @BeforeClass
    public void setTheBrowser(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.inmotionhosting.com/");
    }
    @BeforeMethod
    public void setUpDrivers(Method method) {

        test = extent.createTest(method.getName());

        p01_homepage = new P01_Homepage(driver);
        p02_domainsPage = new P02_DomainsPage(driver);
        p03_cartPage = new P03_CartPage(driver);
        p04_hostPage = new P04_HostPage(driver);
    }
    @AfterMethod
    public void afterEachTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
            takeScreenshot(result.getName() + ".png");
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed ✅");
        } else {
            test.skip("Test Skipped ❌");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush(); // write everything to the report
        }
    }

    public void takeScreenshot(String fileName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), new File("src/test/resources/screenshots/" + fileName).toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
