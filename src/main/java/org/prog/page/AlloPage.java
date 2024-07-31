package org.prog.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlloPage {

    private WebDriver driver;
    private static final By COOKIE_ACCEPT_BUTTON_XPATH = By.xpath("//button[contains(text(), 'Accept') or contains(text(), 'Принять')]");
    private static final By NOTIFICATION_CLOSE_BUTTON_XPATH = By.xpath("//button[contains(@class, 'notification-close') or contains(text(), 'Close')]");
    private static final By SEARCH_BOX_NAME = By.name("search");
    private static final By FIRST_PRODUCT_LINK_XPATH = By.xpath("//div[@class='product-card__img']/a");
    private static final By PRODUCT_PRICE_XPATH = By.xpath("//div[contains(@class, 'v-pb__cur')]//span[contains(@class, 'sum')]");

    public AlloPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookieIfPresent() {
        try {
            WebElement cookieButton = driver.findElement(COOKIE_ACCEPT_BUTTON_XPATH);
            if (cookieButton != null) {
                cookieButton.click();
            }
        } catch (Exception e) {
            System.out.println("No cookies pop-up found or exception occurred: " + e.getMessage());
        }
    }

    public void closeNotificationIfPresent() {
        try {
            WebElement notificationButton = driver.findElement(NOTIFICATION_CLOSE_BUTTON_XPATH);
            if (notificationButton != null) {
                notificationButton.click();
            }
        } catch (Exception e) {
            System.out.println("No notification pop-up found or exception occurred: " + e.getMessage());
        }
    }

    public void executeSearch() {
        WebElement searchBox = driver.findElement(SEARCH_BOX_NAME);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void setParameter(String value) {
        WebElement searchBox = driver.findElement(SEARCH_BOX_NAME);
        searchBox.sendKeys(value);
    }
}
