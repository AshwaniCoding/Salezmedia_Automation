package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAllOrdersTest extends BaseTest{

    @Test
    public void getAllOrderDetails(){

        homePage.openWebsite();
        Assert.assertTrue(homePage.isHomePageDisplayed());

        homePage.clickOnHamburgerMenu();
        homePage.clickOnOrdersBtn();

        Assert.assertTrue(ordersPage.isOrdersPageDisplayed());

        ordersPage.createAndSaveOrdersData();


    }

}
