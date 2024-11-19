package com.automation.pages;

import com.automation.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    WebDriver driver;
    Actions actions;

    public BasePage(){
        driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

}
