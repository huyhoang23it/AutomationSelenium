package fptu.econest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//a[text()='Đăng Nhập']")
    WebElement loginClick;
    @FindBy(name = "email")
    WebElement uName;
    @FindBy(name = "password")
    WebElement pass;
    @FindBy(xpath = "//button[text()='Đăng Nhập']")
    WebElement loginBtn;
    public void loginPortal(String userName, String password){
        loginClick.click();
        uName.sendKeys(userName);
        pass.sendKeys(password);
        loginBtn.click();
    }
}
