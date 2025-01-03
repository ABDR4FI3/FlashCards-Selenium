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
    void TestLoginSuccessful() throws InterruptedException {
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

    @Test
    void TestLoginUnsuccessful() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(BASE_URL);
            WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
            WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            username.sendKeys("a43");
            password.sendKeys("133"); // Wrong Password
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div[2]/div/form/div[4]/button"));
            loginButton.click();
            Thread.sleep(1000);
            WebElement succes = driver.findElement(By.id("error-message"));
            assertEquals(succes.getText(), "Invalid username or password", "Title should match");

        } finally {
            driver.quit();
        }
    }

    @Test
    void TestShowPassword() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get(BASE_URL);
            WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
            WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            username.sendKeys("a43");
            password.sendKeys("133"); // Wrong Password
            assertEquals(password.getAttribute("type"), "password", "Password field type should be password initially");
            WebElement showPassword = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div[2]/div/form/div[2]/div/button"));
            showPassword.click();
            assertEquals(password.getAttribute("type"), "text", "Password field type should change to text after clicking show password");
        } finally {
            driver.quit();
        }
    }

    @Test
    void TestSwitchToSignUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(BASE_URL);
            WebElement Title = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div[1]/h2"));
            assertEquals("Sign in to your account",Title.getText(),"Title should match");
            WebElement signUpLink = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div[1]/p/a"));
            signUpLink.click();
            Thread.sleep(1000);
            WebElement titleSignUp = driver.findElement(By.xpath("/html/body/app-root/app-signup/div/div[1]/h2"));
            assertEquals("Create a new account", titleSignUp.getText(),"Title should match");
            String url = driver.getCurrentUrl();
            assertEquals(url, "http://localhost:4200/signup", "URL should match");
        } finally {
            driver.quit();
        }
    }


}
