package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class P03_CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
     private By domainPriceInCart = By.xpath("//td[contains(@class,'mat-column-price')]//span[contains(text(),'$')]");
     private By domainNameInCart = By.xpath("//td[contains(@class,'mat-column-product')]//div[contains(text(), ' myautomationtest123.com ')]");
     private By mainPage = By.xpath("//a//img[@class=\"amp-brand-logo\"]");
    private By powerPlanInCart = By.xpath("//td[contains(@class,'mat-column-product')]//div[contains(text(), ' Power ')]");
    private By launchPlanInCart = By.xpath("//td[contains(@class,'mat-column-product')]//div[contains(text(), ' Launch ')]");
    private By priceOfItems = By.xpath("//td[contains(@class,'mat-column-price')]//span[contains(text(),'$')][not(contains(@class,'ctw-line-through'))]");
    private By totalElement = By.xpath("//div[contains(text(),'Total')]//span[contains(text(),'$')]");
    private By removeBtnOfDomain = By.xpath("//tr[.//div[contains(text(),'Domain')]]//mat-icon[contains(@aria-label,'Remove')]");
    private By removeBtnOfPower = By.xpath("//tr[.//div[contains(text(),'Power')]]//mat-icon[contains(@aria-label,'Remove')]");

    public P03_CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public String getDomainNameInCart() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(domainNameInCart)).getText();
    }
    public String getDomainPriceInCart() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(domainPriceInCart)).getText();
    }
    public void clickOnMainPage() {
         driver.findElement(mainPage).click();
    }

    public String getPowerPlanInCart() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(powerPlanInCart)).getText();
    }
    public String getLaunchPlanInCart() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(launchPlanInCart)).getText();
    }
    public double getItemPrices() {
        List<WebElement> prices = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(priceOfItems));
        double sum =0.0;

        for (WebElement priceItem : prices) {
            String priceText = priceItem.getText().replace("$", "").trim();
            double price = Double.parseDouble(priceText);
            sum += price;

        }
        return sum;
    }

    public double getTotalPrice() {
        String total = wait.until(ExpectedConditions.visibilityOfElementLocated(totalElement)).getText().replace("$", "").replace("USD","").trim();
        return Double.parseDouble(total);
    }

    public void removeDomain() {
        wait.until(ExpectedConditions.elementToBeClickable(removeBtnOfDomain)).click();
        // Wait until itsdisappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(removeBtnOfDomain));
    }

    public void removePlan() {
        wait.until(ExpectedConditions.elementToBeClickable(removeBtnOfPower)).click();
        // Wait until it disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(removeBtnOfPower));
    }


}
