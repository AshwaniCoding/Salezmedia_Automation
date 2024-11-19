package com.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class DriverManager {

    static WebDriver driver;
    static int screenshotNumber=1;

    public static void createDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static ByteArrayInputStream takeScreenshot(){
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

        File file = ts.getScreenshotAs(OutputType.FILE);
        String folderPath = "src/test/resources/screenshots/";
        String fileName = "screenshot"+(screenshotNumber++)+".png";

        try {
            FileUtils.copyFile(file, new File(folderPath+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ByteArrayInputStream(screenshot);
    }

}
