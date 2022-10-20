package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver driver) {
        this.wd = driver;
    }

    public void type(By locator, String text) {
        if (text != null && !text.isEmpty()) {
            WebElement el = wd.findElement(locator);
            el.click();
            el.clear();
            el.sendKeys(text);
        }
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void pause(int second) {
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isElementPresent(By locator){
        return wd.findElements(locator).size()>0;
    }
    public boolean loginSuccess() {
        try {
            new WebDriverWait(wd, 5).until(ExpectedConditions.visibilityOf(
                    wd.findElement(By.xpath("//button[@data-test-id='header-member-menu-button']"))));
        }catch (Exception e){
            return false;
        }
        return isElementPresent(By.xpath("//button[@data-test-id='header-member-menu-button']"));
    }
    public String getUrl() {
        return wd.getCurrentUrl();
    }
    public String getTitlePage() {
        return wd.getTitle();
    }

}
