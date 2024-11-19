package com.automation.tests;

import com.automation.pages.HomePage;
import com.automation.pages.OrdersPage;
import com.automation.utils.ConfigReader;
import com.automation.utils.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    HomePage homePage;
    OrdersPage ordersPage;

    @BeforeMethod
    public void setUp(){
        DriverManager.createDriver();
        ConfigReader.initConfig();
        homePage = new HomePage();
        ordersPage = new OrdersPage();
    }

    @AfterMethod
    public void cleanUp(){
        DriverManager.getDriver().quit();
    }

}
