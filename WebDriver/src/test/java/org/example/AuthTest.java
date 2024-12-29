package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthTest {
    private static final String BASE_URL = "http://localhost:4200";
    @Test
    public void TestLoginSuccessful() throws InterruptedException {
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
            Thread.sleep(1000);
            WebElement succes = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div[2]/div/div/div/div/p"));
            assertEquals(succes.getText(), "Login successful! Redirecting to your dashboard...", "Title should match");

        } finally {
            driver.quit();
        }
    }
}
