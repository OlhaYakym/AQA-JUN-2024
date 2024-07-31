package org.prog.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestAllo {
    private WebDriver driver;
    private Connection connection;

    private static final By COOKIE_ACCEPT_BUTTON_XPATH = By.xpath("//button[contains(text(), 'Accept') or contains(text(), 'Принять')]");
    private static final By NOTIFICATION_CLOSE_BUTTON_XPATH = By.xpath("//button[contains(@class, 'notification-close') or contains(text(), 'Close')]");
    private static final By SEARCH_BOX_NAME = By.name("search");
    private static final By FIRST_PRODUCT_LINK_XPATH = By.xpath("//div[@class='product-card__img']/a");
    private static final By PRODUCT_NAME_XPATH = By.xpath("//h1[contains(@class, 'product-title')]");
    private static final By PRODUCT_PRICE_XPATH = By.xpath("//div[contains(@class, 'v-pb__cur')]//span[contains(@class, 'sum')]");

    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
        connectToDB();
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

            List<WebElement> productLinks = driver.findElements(FIRST_PRODUCT_LINK_XPATH);
            if (!productLinks.isEmpty()) {
                WebElement firstProductElement = productLinks.get(0);
                String productUrl = firstProductElement.getAttribute("href");
                driver.get(productUrl);

                Thread.sleep(10000);

                WebElement productNameElement = driver.findElement(PRODUCT_NAME_XPATH);
                String productName = productNameElement.getText();
                System.out.println("Product Name on the product page: " + productName);

                WebElement productPriceElement = driver.findElement(PRODUCT_PRICE_XPATH);
                String productPagePrice = productPriceElement.getText();
                System.out.println("Price on the product page: " + productPagePrice);

                saveProductDataToDB(productName, productPagePrice);
                verifyProductDataInDB(productName, productPagePrice);
            } else {
                System.out.println("Failed to find the first product link on the search page.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveProductDataToDB(String productName, String productPrice) throws SQLException {
        String query = "REPLACE INTO ForTestAllo (ProductName, ProductPrice) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, productName);
            pstmt.setString(2, productPrice);
            pstmt.executeUpdate();
        }
    }

    private void verifyProductDataInDB(String productName, String expectedPrice) throws SQLException {
        String query = "SELECT ProductPrice FROM ForTestAllo WHERE ProductName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String price = rs.getString("ProductPrice");
                if (price.equals(expectedPrice)) {
                    System.out.println("Product data verified: " + productName + " with price: " + price);
                } else {
                    System.out.println("Price mismatch for product: " + productName + ". Expected: " + expectedPrice + ", Found: " + price);
                }
            } else {
                System.out.println("Product not found in the database: " + productName);
            }
        }
    }

    private void connectToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db", "user", "password");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
