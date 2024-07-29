package org.prog.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.List;

public class HomeWorkNG {
    private WebDriver driver;

    private static final By COOKIE_ACCEPT_BUTTON_XPATH = By.xpath("//button[contains(text(), 'Accept') or contains(text(), 'Принять')]");
    private static final By NOTIFICATION_CLOSE_BUTTON_XPATH = By.xpath("//button[contains(@class, 'notification-close') or contains(text(), 'Close')]");
    private static final By SEARCH_BOX_NAME = By.name("search");
    private static final By FIRST_PRODUCT_LINK_XPATH = By.xpath("//div[@class='product-card__img']/a");
    private static final By PRODUCT_PRICE_XPATH = By.xpath("//div[contains(@class, 'v-pb__cur')]//span[contains(@class, 'sum')]");

    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testAlloSearch() {
        try {
            driver.get("https://allo.ua/");

            try {
                WebElement cookieButton = driver.findElement(COOKIE_ACCEPT_BUTTON_XPATH);
                if (cookieButton != null) {
                    cookieButton.click();
                }
            } catch (Exception e) {
                System.out.println("No cookies pop-up found or exception occurred: " + e.getMessage());
            }

            try {
                WebElement notificationButton = driver.findElement(NOTIFICATION_CLOSE_BUTTON_XPATH);
                if (notificationButton != null) {
                    notificationButton.click();
                }
            } catch (Exception e) {
                System.out.println("No notification pop-up found or exception occurred: " + e.getMessage());
            }

            WebElement searchBox = driver.findElement(SEARCH_BOX_NAME);
            searchBox.sendKeys("iPhone 15");
            searchBox.sendKeys(Keys.ENTER);

            Thread.sleep(10000);

            List<WebElement> productPrices = driver.findElements(PRODUCT_PRICE_XPATH);
            if (!productPrices.isEmpty()) {
                String firstProductPrice = productPrices.get(0).getText();
                System.out.println("Price of the first product on the search page: " + firstProductPrice);

                WebElement firstProductElement = driver.findElements(FIRST_PRODUCT_LINK_XPATH).get(0);
                String productUrl = firstProductElement.getAttribute("href");
                driver.get(productUrl);

                Thread.sleep(10000);

                WebElement productPagePriceElement = driver.findElement(PRODUCT_PRICE_XPATH);
                String productPagePrice = productPagePriceElement.getText();
                System.out.println("Price on the product page: " + productPagePrice);

                if (firstProductPrice.equals(productPagePrice)) {
                    System.out.println("Prices match.");
                } else {
                    System.out.println("Prices do not match. Price on the search page: " + firstProductPrice + ", Price on the product page: " + productPagePrice);
                }
            } else {
                System.out.println("Failed to find the price of the first product on the search page.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
