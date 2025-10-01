package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P04_HostPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By webHostingTab = By.cssSelector("[title=\"Web Hosting\"]");
    private By webHostingPlan = By.xpath("//span[contains(text(),'View All Web ')]//span[contains(text(),'Hosting')]");
    private By powerPlanButton = By.xpath("//h3[text()='Power']/following::a[contains(@class,'cta-link') and text()='Select Plan'][3]");
    private By cartIcon = By.xpath("//span[@class=\"imh-ds-icon cart\"]");
    private By addPowerPlan = By.xpath("//h3[text()='Power']/following::a[contains(text(),'Select')][3]");
    private By addlaunchPlan = By.xpath("//h3[text()='Launch']/following::a[contains(text(),'Select')][3]");


    public P04_HostPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void NavigateToHostingPage() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(webHostingTab)).perform();
        driver.findElement(webHostingPlan).click();
    }
    By acceptCookiesBtn = By.id("onetrust-accept-btn-handler");

    public void acceptCookiesIfPresent() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn)).click();
        } catch (Exception e) {
            // No cookies popup displayed → ignore
        }
    }

    public void SelectThePowerPlanButton(){
        wait.until(ExpectedConditions.elementToBeClickable(powerPlanButton)).click();
    }
    public void AddThePowerPlanToTheCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addPowerPlan)).click();
    }
    public void AddTheLaunchPlanToTheCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addlaunchPlan)).click();
    }

    public void NavigateToCartPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon)).click();
    }

}
