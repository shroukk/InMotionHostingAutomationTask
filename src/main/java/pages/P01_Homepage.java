package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class P01_Homepage {
    private WebDriver driver;


 //  private By title= By.xpath("//title");
    private By webHostingTab = By.cssSelector("[title=\"Web Hosting\"]");
    private By domainsSearchPage = By.xpath("//span[contains(text(),'Domains')]");

    public P01_Homepage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String webHostingTabTitle() {
        return driver.findElement(webHostingTab).getText();
    }

    public void NavigateToDomainsPage() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(webHostingTab)).perform();
        driver.findElement(domainsSearchPage).click();
    }






}
