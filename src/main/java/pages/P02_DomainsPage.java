package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P02_DomainsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By domainSearchField = By.xpath("//input[@id=\"domain_search_domain\"]");
    private By searchButton = By.xpath("//button[@id=\"domain_submit\"]");
    private By domainLink = By.xpath("//span[contains(text(),\"DOMAIN\")]");
    private By domainName = By.xpath("//div[@id=\"tld0\"]//div[contains(text(),'.com')]");
    private By availableDomain = By.xpath("//div[@id=\"tld0\"]//span[contains(@class,'mat-button-wrapper')]");
    private By unAvailableDomain = By.xpath("//div[@id=\"tld1\"]//span[contains(@class,'mat-button-wrapper')]");
    private By domainPrice = By.xpath("//div[@id=\"tld0\"]//span[contains(text(), '$')]");
    private By domainNameField = By.xpath("//input[@name=\"domainName\"]");
    private By clickSearchDomain = By.xpath("//span[contains(text(),\"Search Domains \")]");
    private By chooseDomainLaterBtn = By.xpath("//span[contains(text(),\" Choose my domain later \")]");

    public P02_DomainsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void searchDomain(String domain) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(domainSearchField)).sendKeys(domain);
        driver.findElement(searchButton).click();
    }
    public void waitTheResultPage() {
       // https://central.inmotionhosting.com/amp/checkout#complete
         wait.until(ExpectedConditions.urlContains("https://central.inmotionhosting.com/amp/checkout#complete"));
    }
    public void clickDomainLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(domainLink)).click();
    }
    public String getDomainAvail() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(availableDomain)).getText();
    }
    public String getDomainUnavail() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(unAvailableDomain)).getText();
    }
    public void addToCart() {
         wait.until(ExpectedConditions.visibilityOfElementLocated(availableDomain)).click();
    }
    public String getDomainName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(domainName)).getText();
    }
    public String getDomainPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(domainPrice)).getText();
    }
    public void EnterDomainName(String domain) {
         wait.until(ExpectedConditions.visibilityOfElementLocated(domainNameField)).sendKeys(domain);
    }

    public void clickSearchDomain() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(clickSearchDomain)).click();
    }

    public void clickChooseDomainLaterBtn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(chooseDomainLaterBtn)).click();
    }

}
