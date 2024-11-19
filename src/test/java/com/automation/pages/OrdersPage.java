package com.automation.pages;

import com.automation.utils.ConfigReader;
import com.automation.utils.ExcelUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class OrdersPage extends BasePage{

    @FindBy(xpath = "//h2//span[text()='Order']")
    WebElement orderText;

    @FindBy(xpath = "//div[@class='servnm']")
    List<WebElement> serviceList;

    @FindBy(xpath = "//p[contains(text(),'Order ID')]//following-sibling::span//b")
    List<WebElement> orderIdList;

    @FindBy(xpath = "//p[contains(text(),' Link')]//following-sibling::a")
    List<WebElement> orderLinkList;

    @FindBy(xpath = "//p[contains(text(),' Date')]//following-sibling::span")
    List<WebElement> dateTimeList;

    @FindBy(xpath = "//p[contains(text(),' Amount')]//following-sibling::span")
    List<WebElement> amountList;

    @FindBy(xpath = "//p[contains(text(),' Status')]//following-sibling::span")
    List<WebElement> statusList;

    @FindBy(xpath = "//small[contains(text(),'Start Count')]//following-sibling::p")
    List<WebElement> startCountList;

    @FindBy(xpath = "//small[contains(text(),'Quantity')]//following-sibling::p")
    List<WebElement> quantityList;

    @FindBy(xpath = "//small[contains(text(),'Remains')]//following-sibling::p")
    List<WebElement> remainsList;

    @FindBy(xpath = "//a[@class='page-link']//span[text()='»']")
    WebElement nextBtn;


    public boolean isOrdersPageDisplayed() {
        try{
            return orderText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<List<WebElement>> getListOfWebElement(){
        List<List<WebElement>> listOfWebElement = new ArrayList<>();

        listOfWebElement.add(dateTimeList);
        listOfWebElement.add(orderIdList);
        listOfWebElement.add(serviceList);
        listOfWebElement.add(orderLinkList);
        listOfWebElement.add(amountList);
        listOfWebElement.add(statusList);
        listOfWebElement.add(startCountList);
        listOfWebElement.add(quantityList);
        listOfWebElement.add(remainsList);

        return listOfWebElement;
    }

    public void createAndSaveOrdersData() {

        int rowNum = Integer.parseInt(ConfigReader.getConfigValue("start.row.num"));

        ExcelUtils.createWorkbookAndSheet("OrdersData");
        ExcelUtils.setHeader();

        int totalPages = Integer.parseInt(ConfigReader.getConfigValue("last.page"));

        for (int i = 0;i<totalPages;i++){
            rowNum = ExcelUtils.writeDataIntoSheet(rowNum,getListOfWebElement());
            actions.scrollToElement(nextBtn).pause(1000).click(nextBtn).build().perform();
        }
        ExcelUtils.saveIntoFile("SalezMedia.xlsx");

    }
}
