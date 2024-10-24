package fptu.econest.testcase;

import fptu.econest.pages.BasePages;
import fptu.econest.pages.LoginPage;
import fptu.econest.ultilities.ReadExcelFile;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TC_001 extends BasePages{
    String fileName = System.getProperty("user.dir")+"\\TestData\\TestData.xlsx";
    @Test(priority = 1, dataProvider = "LoginDataProvider")
    void verifyLogin(String userName, String password) throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPortal(userName,password);
        if(loginPage.isLoginSuccessful())
        {
            System.out.println("Test Passed");
            Assert.assertTrue(true);
        }
        else
        {
            captureScreenShot(driver,"VerifyLogin");
            Assert.assertTrue(false);
        }
    }
    @DataProvider(name = "LoginDataProvider")
    public Object[][] loginDataProvider() {
        int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginData");
        int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginData");
        List<String[]> validRows = new ArrayList<>();

        for (int i = 1; i < ttlRows; i++) {
            boolean isRowEmpty = true;
            String[] rowData = new String[ttlColumns];

            for (int j = 0; j < ttlColumns; j++) {
                String cellValue = ReadExcelFile.getCellValue(fileName, "LoginData", i, j);
                if (cellValue != null && !cellValue.trim().isEmpty()) {
                    isRowEmpty = false;
                }

                rowData[j] = cellValue;
                System.out.println("Row: " + i + ", Column: " + j + " Value: " + cellValue);
            }
            if (!isRowEmpty) {
                validRows.add(rowData);
            } else {
                System.out.println("Row: " + i + " is empty, skipping...");
            }
        }
        String[][] data = new String[validRows.size()][ttlColumns];
        return validRows.toArray(data);
    }
}
