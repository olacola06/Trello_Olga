package manager;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    EventFiringWebDriver wd;
    String browser;
    Properties properties;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    UserHelper user;
    BoardHelper board;

    public ApplicationManager(String browser) {
        properties = new Properties();
        this.browser = browser;

    }
    public void init() throws IOException {
        String target = System.getProperty("target","config");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));
               if (browser.equals(BrowserType.CHROME)){
               ChromeOptions options = new ChromeOptions();
               options.addArguments("--lang=en");
            wd = new EventFiringWebDriver(new ChromeDriver(options));
            logger.info("All tests starts in 'Chrome' browser");
        }
        else if(browser.equals(BrowserType.EDGE)){
            wd = new EventFiringWebDriver (new EdgeDriver());
            logger.info("All tests starts in 'Edge' browser");
        }
        wd.manage().window().maximize();
        //wd.navigate().to("https://trello.com/");
        wd.navigate().to(properties.getProperty("web.baseUrl"));
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
    public String getEmail(){
        return properties.getProperty("web.email");
    }
    public String getPassword(){
        return properties.getProperty("web.password");
    }

    public void end() {
        wd.quit();
    }

}
