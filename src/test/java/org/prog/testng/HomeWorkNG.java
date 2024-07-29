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

    private static final String COOKIE_ACCEPT_BUTTON_XPATH = "//button[contains(text(), 'Принять') or contains(text(), 'Accept')]";
    private static final String NOTIFICATION_CLOSE_BUTTON_XPATH = "//button[contains(@class, 'notification-close') or contains(text(), 'Закрыть')]";
    private static final String SEARCH_BOX_NAME = "search";
    private static final String SEARCH_BUTTON_XPATH = "//button[@type='submit' and contains(@class, 'search-button')]";
    private static final String PRODUCT_PRICE_XPATH = "//div[contains(@class, 'v-pb__cur')]//span[contains(@class, 'sum')]";

    private WebDriver driver;

    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testAlloSearch() {
        try {
            driver.get("https://allo.ua/");

            Thread.sleep(2000);
            try {
                List<WebElement> cookieButtons = driver.findElements(By.xpath(COOKIE_ACCEPT_BUTTON_XPATH));
                if (!cookieButtons.isEmpty()) {
                    cookieButtons.get(0).click();
                }
            } catch (Exception e) {
                System.out.println("No cookies pop-up found or exception occurred: " + e.getMessage());
            }

            try {
                List<WebElement> notificationCloseButtons = driver.findElements(By.xpath(NOTIFICATION_CLOSE_BUTTON_XPATH));
                if (!notificationCloseButtons.isEmpty()) {
                    notificationCloseButtons.get(0).click();
                }
            } catch (Exception e) {
                System.out.println("No notification pop-up found or exception occurred: " + e.getMessage());
            }

            WebElement searchBox = driver.findElement(By.name(SEARCH_BOX_NAME));
            searchBox.sendKeys("iPhone 15");
            searchBox.sendKeys(Keys.ENTER);

            Thread.sleep(10000);

            List<WebElement> productPrices = driver.findElements(By.xpath(PRODUCT_PRICE_XPATH));
            if (!productPrices.isEmpty()) {
                String firstProductPrice = productPrices.get(0).getText();
                System.out.println("Price of the first product: " + firstProductPrice);
            } else {
                System.out.println("No products found or prices not visible.");
            }

        } catch (InterruptedException e) {
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
