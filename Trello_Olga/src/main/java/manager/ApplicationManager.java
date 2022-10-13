package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    EventFiringWebDriver wd;
    String browser;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    UserHelper user;
    BoardHelper board;

    public ApplicationManager(String browser) {
        this.browser = browser;

    }
    public void init() {
        if (browser.equals(BrowserType.CHROME)){
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("All tests starts in 'Chrome' browser");
        }
        else if(browser.equals(BrowserType.EDGE)){
            wd = new EventFiringWebDriver(new EdgeDriver());
            logger.info("All tests starts in 'Edge' browser");
        }
        wd.manage().window().maximize();
        wd.navigate().to("https://trello.com/");
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        user = new UserHelper(wd);
        board = new BoardHelper(wd);
    }
    public UserHelper getUser(){
        return user;
    }
    public BoardHelper getBoard(){
        return board;
    }

    public void end() {
        wd.quit();
    }

}
