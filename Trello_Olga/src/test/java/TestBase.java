import manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {
    protected static ApplicationManager app = new ApplicationManager
            (System.getProperty("browser", BrowserType.CHROME));

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void startLogger(Method m){
        logger.info("Starts method: "+m.getName());
    }
    @AfterMethod
    public void finishLogger(Method m){
        logger.info("Method "+m.getName()+": finished");
    }
    @BeforeSuite
    public void start(){
        app.init();
    }

    @AfterSuite(enabled = false)
    public void finish(){
        app.end();

    }
}
