package com.automation.pages;

import com.automation.utils.ConfigReader;
import com.automation.utils.DriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(css = ".hamburget")
    WebElement hamburgerMenuBtn;

    @FindBy(xpath = "(//a[@href='/orders'])[2]")
    WebElement ordersBtn;

    @FindBy(xpath = "//h2[contains(text(),'Welcome to')]")
    WebElement welcomeText;

    public void openWebsite(){
        driver.get(ConfigReader.getConfigValue("application.url"));
        Cookie cookie = new Cookie("PHPSESSID",ConfigReader.getConfigValue("session.id"));
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }

    public boolean isHomePageDisplayed() {
        try{
            return welcomeText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnHamburgerMenu() {
        hamburgerMenuBtn.click();
    }

    public void clickOnOrdersBtn() {
        ordersBtn.click();
    }
}
