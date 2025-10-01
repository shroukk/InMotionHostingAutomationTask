package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class T02_DomainSearch extends T01_BaseTest {

    String domain_Name,domain_total_price;
    @Test (priority = 1)
    public void testValidDomain(){


        SoftAssert softAssert = new SoftAssert();


        softAssert.assertTrue(p01_homepage.getTitle().contains("InMotion Hosting"), "Title check failed");
        softAssert.assertTrue(p01_homepage.webHostingTabTitle().contains("Web Hosting"), "Web hosting tab failed");
        p01_homepage.NavigateToDomainsPage();
        p02_domainsPage.searchDomain("myautomationtest123.com");
        p02_domainsPage.waitTheResultPage();
        p02_domainsPage.clickDomainLink();
        softAssert.assertTrue(p02_domainsPage.getDomainAvail().contains("Add"),"Domain not avail");
        softAssert.assertEquals(p02_domainsPage.getDomainPrice(),"$23.00 /yr", "Price not matching");

        // Take screenshot regardless of failures
        //takeScreenshot("soft_assert_domain.png");

        domain_Name = p02_domainsPage.getDomainName();
        domain_total_price = p02_domainsPage.getDomainPrice();
        p02_domainsPage.addToCart();

        softAssert.assertTrue(p03_cartPage.getDomainNameInCart().contains(domain_Name));
        softAssert.assertTrue(p03_cartPage.getDomainPriceInCart().contains(domain_total_price));
        p03_cartPage.clickOnMainPage();
        p04_hostPage.NavigateToHostingPage();
        p04_hostPage.acceptCookiesIfPresent();
        p04_hostPage.SelectThePowerPlanButton();
        p04_hostPage.AddThePowerPlanToTheCart();
        p02_domainsPage.EnterDomainName("myautomationtest123.com");
        p02_domainsPage.clickSearchDomain();
        p02_domainsPage.waitTheResultPage();
        softAssert.assertTrue(p03_cartPage.getDomainNameInCart().contains(domain_Name));
        softAssert.assertTrue(p03_cartPage.getPowerPlanInCart().contains("Power"));
        softAssert.assertEquals(p03_cartPage.getItemPrices(),p03_cartPage.getTotalPrice()," items prices not match the total");

        softAssert.assertAll();
    }



    @Test (priority = 2)
    public void testCartPersistenceAfterRefresh() {
        SoftAssert softAssert = new SoftAssert();

        // Arrange: already have domain + hosting added
        String domainBefore = p03_cartPage.getDomainNameInCart();
        String hostingBefore = p03_cartPage.getPowerPlanInCart();
        double pricesBefore = p03_cartPage.getItemPrices();
        double totalBefore = p03_cartPage.getTotalPrice();

        // Act: Refresh the page
        driver.navigate().refresh();

        // Assert: Verify persistence
        softAssert.assertEquals(p03_cartPage.getDomainNameInCart(), domainBefore, "Domain name changed after refresh!");
        softAssert.assertEquals(p03_cartPage.getPowerPlanInCart(), hostingBefore, "Hosting plan changed after refresh!");

        double pricesAfter = p03_cartPage.getItemPrices();
        double totalAfter = p03_cartPage.getTotalPrice();

        softAssert.assertEquals(pricesAfter, pricesBefore, "Item prices changed after refresh!");
        softAssert.assertEquals(totalAfter, totalBefore, "Total price changed after refresh!");
        softAssert.assertAll();

    }

    @Test (priority = 3)
    public void testRemoveDomainFromCart() {
        SoftAssert softAssert = new SoftAssert();

        p03_cartPage.removeDomain();
        p02_domainsPage.clickChooseDomainLaterBtn();
        softAssert.assertTrue(p03_cartPage.getPowerPlanInCart().contains("Power"));
        softAssert.assertAll();
    }

    @Test (dependsOnMethods = "testRemoveDomainFromCart")
    public void testUpdateHostingPlan() {
        SoftAssert softAssert = new SoftAssert();

        p03_cartPage.removePlan();
        p04_hostPage.NavigateToHostingPage();
        p04_hostPage.SelectThePowerPlanButton();
        p04_hostPage.AddTheLaunchPlanToTheCart();
        p02_domainsPage.clickChooseDomainLaterBtn();
        // p04_hostPage.NavigateToCartPage();
        //p02_domainsPage.waitTheResultPage();
        softAssert.assertTrue(p03_cartPage.getLaunchPlanInCart().contains("Launch"));
        softAssert.assertEquals(p03_cartPage.getItemPrices(),p03_cartPage.getTotalPrice()," items prices not match the total");
        softAssert.assertAll();


    }
    @Test (dependsOnMethods = "testUpdateHostingPlan")
    public void testInvalidDomain() {
        p03_cartPage.clickOnMainPage();

        SoftAssert softAssert = new SoftAssert();
        p01_homepage.NavigateToDomainsPage();
        p02_domainsPage.searchDomain("google.com");
        softAssert.assertTrue(p02_domainsPage.getDomainUnavail().contains("Unavailable"),"Domain avail");
        softAssert.assertAll();

    }



}
