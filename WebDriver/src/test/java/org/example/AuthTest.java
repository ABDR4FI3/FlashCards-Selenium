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

    @Test
    public void TestLoginUnsuccessful() throws InterruptedException {
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
    public void TestShowPassword() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(BASE_URL);
            WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
            WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            username.sendKeys("a43");
            password.sendKeys("133"); // Wrong Password
            WebElement showPassword = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div[2]/div/form/div[2]/div/button"));
            WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            assertEquals(passwordField.getAttribute("value"), "133", "Password should match");
        } finally {
            driver.quit();
        }
    }

    @Test
    public void TestSwitchToSignUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(BASE_URL);
            WebElement Title = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div[1]/h2"));
            assertEquals(Title.getText(), "Sign in to your account", "Title should match");
            WebElement signUpLink = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div[1]/p/a"));
            signUpLink.click();
            Thread.sleep(1000);
            WebElement TitleSignUp = driver.findElement(By.xpath("/html/body/app-root/app-signup/div/div[1]/h2"));
            assertEquals(TitleSignUp.getText(), "Create a new account", "Title should match");
            String url = driver.getCurrentUrl();
            assertEquals(url, "http://localhost:4200/signup", "URL should match");
        } finally {
            driver.quit();
        }
    }

}
