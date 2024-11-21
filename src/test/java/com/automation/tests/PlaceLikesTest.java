package com.automation.tests;

import com.automation.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class PlaceLikesTest extends BaseTest{

    @Test(dataProvider = "getData")
    public void verifyUserCanPlaceOrder(String link, String quantity){

       homePage.openWebsite();
       homePage.selectInstagramLikesCategoryOption();
       homePage.selectInstagramLikesBestSellerServiceOption();
       homePage.fillLink(link);
       homePage.fillQuantity(quantity);
       homePage.clickOnSubmitBtn();

        Assert.assertTrue(homePage.isOrderPlacedSuccessfully());

    }

    @DataProvider
    public Object[][] getData(){
        ExcelUtils excelUtils = new ExcelUtils("placeOrder.xlsx");
        List<List<String>> tableData = excelUtils.getData();
        Object[][] data = new Object[tableData.size()][tableData.getFirst().size()];

        for(int i=0;i<tableData.size();i++){
            List<String> rowData = tableData.get(i);
            for(int j=0;j< rowData.size();j++){
                data[i][j] = rowData.get(j);
            }
        }

        return data;

    }

}
