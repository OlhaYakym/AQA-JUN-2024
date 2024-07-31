package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.prog.driver.WedDriverFactory;
import org.prog.page.AlloPage;
import org.prog.util.CucumberStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WebSteps {

    private static WebDriver driver = WedDriverFactory.getDriver();
    private static Connection connection;
    public static AlloPage alloPage = new AlloPage(driver);

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "password");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Given("I load allo page")
    public void loadAlloPage() {
        driver.get("https://allo.ua/");
        alloPage.acceptCookieIfPresent();
        alloPage.closeNotificationIfPresent();
    }

    @Given("I take value from {string} and send it to allo search")
    public void setSearchValueToAlias(String alias) {
        String value = (String) CucumberStorage.HOLDER.get(alias);
        alloPage.setParameter(value);
    }

    @When("I perform search")
    public void executeSearch() {
        alloPage.executeSearch();
    }

    @Then("I check if phone {string} is in db and if price is changed I update good price with {string}")
    public void checkAndUpdatePhoneInDB(String phone, String price) throws SQLException {
        String query = "SELECT ProductPrice FROM ForTestAllo WHERE ProductName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, phone);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String dbPrice = rs.getString("ProductPrice");
                if (!dbPrice.equals(price)) {
                    System.out.println("Updating price for product: " + phone + " from " + dbPrice + " to " + price);
                    updateProductPrice(phone, price);
                } else {
                    System.out.println("Price is already up-to-date for product: " + phone);
                }
            } else {
                System.out.println("Product not found in the database: " + phone);
            }
        }
    }

    @Then("if phone is not in db I insert good {string} and I insert good {string} into db")
    public void insertProductIntoDB(String phone, String price) throws SQLException {
        String query = "REPLACE INTO ForTestAllo (ProductName, ProductPrice) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, phone);
            pstmt.setString(2, price);
            pstmt.executeUpdate();
            System.out.println("Inserted product into DB: " + phone + " with price: " + price);
        }
    }

    private void updateProductPrice(String phone, String price) throws SQLException {
        String query = "UPDATE ForTestAllo SET ProductPrice = ? WHERE ProductName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, price);
            pstmt.setString(2, phone);
            pstmt.executeUpdate();
        }
    }
}
