package fptu.econest.testcase;

import fptu.econest.pages.BasePages;
import fptu.econest.pages.LoginPage;
import org.testng.annotations.Test;

public class TC_001 extends BasePages{
    @Test
    void verifyLogin(){
        LoginPage loginPage = new LoginPage(driver);
        String userName = "12345@gmail.com";
        String password = "123";
        loginPage.loginPortal(userName,password);
    }
}
