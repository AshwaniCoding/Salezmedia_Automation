package com.automation.pages;

import com.automation.utils.ConfigReader;
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

    @FindBy(xpath = "//label[text()='Category']//following-sibling::span//span//span")
    WebElement categoryOption;

    @FindBy(xpath = "//label[text()='Service ']//following-sibling::span//span//span")
    WebElement serviceOption;

    @FindBy(xpath = "(//li//a//span[contains(text(),'Instagram Likes')])[2]")
    WebElement instagramLikes;

    @FindBy(xpath = "//li//a//span[contains(text(),'$0.0455')]")
    WebElement bestSellerLikes;

    @FindBy(xpath = "//label[text()='Link']//following-sibling::input")
    WebElement linkInput;

    @FindBy(xpath = "//label[text()='Quantity']//following-sibling::input")
    WebElement quantityInput;

    @FindBy(xpath = "//button[text()='Submit']")
    WebElement submitButton;

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

    public void selectInstagramLikesCategoryOption() {
        categoryOption.click();
        instagramLikes.click();
    }

    public void selectInstagramLikesBestSellerServiceOption() {
        serviceOption.click();
        bestSellerLikes.click();
    }

    public void fillLink(String link) {
        linkInput.sendKeys(link);
    }

    public void fillQuantity(String quantity) {
        quantityInput.sendKeys(quantity);
    }

    public void clickOnSubmitBtn() {
        submitButton.click();
    }

    public boolean isOrderPlacedSuccessfully() {

    }
}
