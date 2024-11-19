package com.automation.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ExcelUtils(String fileName){
        try {
            workbook = new XSSFWorkbook("src/test/resources/excelFiles/" + fileName);
            sheet = workbook.getSheetAt(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveIntoFile(String fileName) {
        String folderPath = "src/test/resources/excel-files/";
        try (FileOutputStream fileOut = new FileOutputStream(folderPath + fileName)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTotalNumOfRow(){
        return sheet.getLastRowNum();
    }

    public int getTotalNumOfCol(){
        return sheet.getRow(0).getLastCellNum();
    }

    public List<List<String>> getData(){
        List<List<String>> tableData = new ArrayList<>();
        for(int i=1;i<=getTotalNumOfRow();i++){
            XSSFRow row = sheet.getRow(i);
            List<String> rowData = new ArrayList<>();
            for(int j=0;j<getTotalNumOfCol();j++){
                rowData.add(row.getCell(j).getStringCellValue());
            }
            tableData.add(rowData);
        }
        return tableData;
    }

    public static void createWorkbookAndSheet(String sheetName){
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }

    public static void setHeader(){
        String[] headers = {"S No","DateTime", "OrderID","ServiceName","Link", "Amount", "Status", "StartCount", "Quantity", "Remains"};
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }

    public static int writeDataIntoSheet(int rowNum, List<List<WebElement>> elements) {
        // Calculate the minimum number of rows across all columns
        int totalRows = elements.stream()
                .mapToInt(List::size)
                .min()
                .orElse(0);

        // Populate data rows
        for (int i = 0; i < totalRows; i++) {
            Row row = sheet.createRow(rowNum++); // Create a new row in the Excel sheet

            // Loop through each column (List<WebElement>)
            for (int j = 0; j < elements.size(); j++) {
                List<WebElement> column = elements.get(j);

                // Check if the current row exists in the column
                if (i < column.size()) {
                    WebElement element = column.get(i);
                    String cellValue = (j == 3) ? element.getAttribute("href") : element.getText();
                    row.createCell(j + 1).setCellValue(cellValue); // Write data to the cell
                } else {
                    row.createCell(j + 1).setCellValue(""); // Add empty value for missing data
                }
            }

            // Add the row number to the first cell
            row.createCell(0).setCellValue(rowNum - 1);
        }

        // Auto-size columns for a better appearance
        for (int i = 0; i <= elements.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        return rowNum;
    }


}
