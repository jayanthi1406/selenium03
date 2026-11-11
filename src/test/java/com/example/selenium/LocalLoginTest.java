
package com.example.selenium;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocalLoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        File file = new File("webpage/login.html");
        String page = file.getAbsolutePath();

        driver.get("file:///" + page.replace("\\", "/"));
    }


    public void validLoginTest() {

        driver.findElement(By.id("username")).sendKeys("student");

        driver.findElement(By.id("password")).sendKeys("12345");

        driver.findElement(By.id("loginButton")).click();

        String message = driver.findElement(By.id("message")).getText();

        System.out.println("Message: " + message);

        Assert.assertEquals(message, "Login Successful",
                "Valid login test failed!");
    }

    @Test
    public void invalidLoginTest() {

        driver.findElement(By.id("username")).sendKeys("wrong");

        driver.findElement(By.id("password")).sendKeys("wrong");

        driver.findElement(By.id("loginButton")).click();

        String message = driver.findElement(By.id("message")).getText();

        System.out.println("Message: " + message);

        Assert.assertEquals(message, "Invalid Username or Password",
                "Invalid login test failed!");
    }

    @Test
    public void checkBackgroundColor() {

        String bgColor = driver.findElement(By.id("loginBox"))
                .getCssValue("background-color");

        System.out.println("Background Color: " + bgColor);

        Assert.assertEquals(bgColor, "rgba(173, 216, 230, 1)",
                "Background color does not match!");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
