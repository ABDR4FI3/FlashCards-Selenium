package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardTest {
    private static final String BASE_URL = "http://localhost:4200";
    @Test
    void TestCategoriesDisplayed() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(BASE_URL);
            WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
            WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            username.sendKeys("a43");
            password.sendKeys("123");
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div[2]/div/form/div[4]/button"));
            loginButton.click();
            Thread.sleep(4000);
            // Find all elements with the tag name "app-category-card"
            List<WebElement> categoryCards = driver.findElements(By.tagName("app-category-card"));
            int expectedCount = 7;
            assertEquals(expectedCount, categoryCards.size(), "The count of app-category-card should match the expected count");

            System.out.println("Test passed. Found " + categoryCards.size() + " app-category-cards.");

        } finally {
            driver.quit();
        }
    }
    @Test
    void TestQuizSetsDisplayed() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get(BASE_URL);
            WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
            WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            username.sendKeys("a43");
            password.sendKeys("123");
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div[2]/div/form/div[4]/button"));
            loginButton.click();
            Thread.sleep(4000);
            // Find all elements with the tag name "app-category-card"
            List<WebElement> categoryCards = driver.findElements(By.tagName("app-category-card"));
            int expectedCount = 7;
            assertEquals(expectedCount, categoryCards.size(), "The count of app-category-card should match the expected count");

            WebElement firstCategory = categoryCards.get(0);
            firstCategory.click();
            Thread.sleep(4000);
            List<WebElement> quizSets = driver.findElements(By.tagName("app-quiz-set-card"));
            int expectedCount2 = 3;
            assertEquals(expectedCount2, quizSets.size(), "The count of app-quiz-card should match the expected count");
            System.out.println("Test passed. Found " + quizSets.size() + " app-quiz-cards.");

        } finally {
            driver.quit();
        }
    }
}
