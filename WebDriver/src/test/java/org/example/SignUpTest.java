package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpTest {
    private static final String BASE_URL = "http://localhost:4200/signup";
    @Test
    void testSignUpSuccessful() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Random random = new Random();
        String user = random.nextInt(10000000) + "";
        try {
            driver.get(BASE_URL);
            WebElement email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
            WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
            WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            email.sendKeys(user + "@gmail.com");
            username.sendKeys(user + "");
            password.sendKeys("1235678");
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/app-signup/div/div[2]/div/form/div[4]/button"));
            loginButton.click();
            Thread.sleep(1000);
            WebElement succes = driver.findElement(By.id("success-message"));
            assertEquals(succes.getText(), "Account created Redirecting to your dashboard...", "Title should match");
            Thread.sleep(6000);
            String url = driver.getCurrentUrl();
            assertEquals(url, "http://localhost:4200/dashboard", "URL should match");
            WebElement Title = driver.findElement(By.xpath("/html/body/app-root/app-home/div/h1"));
            assertEquals("Pick a set to practice",Title.getText(),"Title should match");

        } finally {

            driver.quit();
        }
    }

    @Test
    void testSignUpDuplicateUserName() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Random random = new Random();
        String user = random.nextInt(10000000) + "";
        try {
            driver.get(BASE_URL);
            WebElement email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
            WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
            WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            email.sendKeys(user + "@gmail.com");
            username.sendKeys("a43");
            password.sendKeys("1235678");
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/app-signup/div/div[2]/div/form/div[4]/button"));
            loginButton.click();
            Thread.sleep(1000);
            WebElement succes = driver.findElement(By.id("error-message"));
            assertEquals(succes.getText(), "409 Conflict: \"{\"errorMessage\":\"User exists with same username\"}\"", "Title should match");

            String url = driver.getCurrentUrl();
            assertEquals(url, "http://localhost:4200/signup", "URL should match");

        } finally {

            driver.quit();
        }
    }
    @Test
    void testSignUpDuplicateEmail() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Random random = new Random();
        String user = random.nextInt(10000000) + "";
        try {
            driver.get(BASE_URL);
            WebElement email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
            WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
            WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            email.sendKeys( "a43@gmail.com");
            username.sendKeys(user);
            password.sendKeys("1235678");
            WebElement loginButton = driver.findElement(By.xpath("/html/body/app-root/app-signup/div/div[2]/div/form/div[4]/button"));
            loginButton.click();
            Thread.sleep(1000);
            WebElement succes = driver.findElement(By.id("error-message"));
            assertEquals(succes.getText(), "409 Conflict: \"{\"errorMessage\":\"User exists with same email\"}\"", "Title should match");
            String url = driver.getCurrentUrl();
            assertEquals(url, "http://localhost:4200/signup", "URL should match");

        } finally {
            driver.quit();
        }
    }


}
