package fptu.econest.pages;

import fptu.econest.ultilities.BrowserFactory;
import fptu.econest.ultilities.ConfigDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BasePages {
    public WebDriver driver;
    public ConfigDataProvider configDataProvider = new ConfigDataProvider();
    @BeforeClass
    public void setUp(){
        driver = BrowserFactory.startApplication(driver,configDataProvider.getBrowser(),configDataProvider.getStagingUrl());
    }
    @AfterClass
    public void tearDown(){
        BrowserFactory.quitBrowser(driver);
    }
}
